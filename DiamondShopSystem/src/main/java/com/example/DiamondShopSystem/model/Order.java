package com.example.DiamondShopSystem.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "`order`") // `order` is a reserved keyword in SQL, hence backticks are used
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    private String username;

    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private OrderStatus orderStatus;

    @Column(nullable = true)
    private float totalPrice;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
