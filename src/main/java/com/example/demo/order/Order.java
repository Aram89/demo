package com.example.demo.order;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private long orderId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items;

    @Column
    private String email;

    @Column
    private double amount;

    @Column
    private LocalDateTime time;

    Order(List<OrderItem> items, String email, LocalDateTime time, double amount) {
        this.items = items;
        this.email = email;
        this.time = time;
        this.amount = amount;
    }
}
