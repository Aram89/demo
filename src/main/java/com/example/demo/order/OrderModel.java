package com.example.demo.order;

import com.example.demo.product.ProductModel;
import lombok.Data;

import java.util.List;

@Data
class OrderModel {

    private List<ProductModel> products;
    private String email;
}
