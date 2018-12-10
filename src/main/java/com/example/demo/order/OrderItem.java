package com.example.demo.order;

import com.example.demo.product.Product;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Data
class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private long quantity;

    OrderItem(Product product, long quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
