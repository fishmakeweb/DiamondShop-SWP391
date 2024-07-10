package com.example.DiamondShopSystem.dto;

import java.util.Date;

public class OrderChatDTO {
    private Long orderId;
    private String username;
    private String orderStatus;
    private Date orderDate;
    private double totalPrice;

    public OrderChatDTO(Long orderId, String username, String orderStatus, Date orderDate, double totalPrice) {
        this.orderId = orderId;
        this.username = username;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
