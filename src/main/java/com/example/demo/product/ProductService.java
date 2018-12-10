package com.example.demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    Iterable<Product> getAll() {
        return productRepo.findAll();
    }

    void update(ProductModel productModel) {
        Product product = convert(productModel);

        productRepo.findByName(product.getName())
                .map(productRepo::save)
                .orElseThrow(() -> new EntityNotFoundException(product.getName()));
    }

    void add(ProductModel productModel) {
        Product product = convert(productModel);

        productRepo.save(product);
    }

    private Product convert(ProductModel model) {
        return new Product(model.getName(), model.getPrice());
    }
}
