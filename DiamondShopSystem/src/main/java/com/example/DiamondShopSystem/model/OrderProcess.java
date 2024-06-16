package com.example.DiamondShopSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orderprocess")
public class OrderProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderprocessId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "orderprocess_description", nullable = false, length = 200)
    private String orderProcessDescription;

    // Getters and Setters
    public Long getOrderprocessId() {
        return orderprocessId;
    }

    public void setOrderprocessId(Long orderprocessId) {
        this.orderprocessId = orderprocessId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getOrderProcessDescription() {
        return orderProcessDescription;
    }

    public void setOrderProcessDescription(String orderProcessDescription) {
        this.orderProcessDescription = orderProcessDescription;
    }
}