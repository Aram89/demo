package com.example.demo.order;

import com.example.demo.exception.AppException;
import com.example.demo.product.Product;
import com.example.demo.product.ProductModel;
import com.example.demo.product.ProductRepo;
import one.util.streamex.StreamEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo, ProductRepo productRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    void save(OrderModel request) {
        /* load and check products from database to be sure that prices in request are correct */
        List<Product> products = StreamEx.of(request.getProducts())
                .map(this::checkProduct).toList();

        /* Counting each product quantity in order */
        Map<Product, Long> productCount = StreamEx.of(products).
                groupingBy(Function.identity(), Collectors.counting());

        /* Create orderItems from products */
        List<OrderItem> orderItems = StreamEx.of(productCount.entrySet())
                .map(entry -> new OrderItem(entry.getKey(), entry.getValue()))
                .toList();

        /* Calculate order amount, here we can be sure that have correct prices */
        double amount = StreamEx.of(products)
                .map(Product::getPrice)
                .reduce((a,b) -> a + b)
                .orElse(0.0);

        Order order = new Order(orderItems, request.getEmail(), LocalDateTime.now(), amount);

        orderRepo.save(order);
    }

    private Product checkProduct(ProductModel model) {
        Product product = productRepo.findByName(model.getName())
                .orElseThrow(() -> new EntityNotFoundException(model.getName()));

        if(product.getPrice() != model.getPrice()) {
            throw new AppException("Product price in request and actual price are not the same");
        }

        return product;
    }
}
