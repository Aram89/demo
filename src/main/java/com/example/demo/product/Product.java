package com.example.demo.product;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private long productId;

    @Column(unique = true)
    private String name;

    @Column
    private double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
