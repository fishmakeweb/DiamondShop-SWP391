package com.example.DiamondShopSystem.model;


import jakarta.persistence.*;

@Entity
public class PaymentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderCode;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private long expiredAt;

    @Column(nullable = false)
    private String returnUrl;

    @Column(nullable = false)
    private String cancelUrl;

    public PaymentRequest() {
//        this.orderCode = orderCode;
    }

    public PaymentRequest(int orderCode, double amount, String description, long expiredAt, String returnUrl, String cancelUrl) {
        this.orderCode = orderCode;
        this.amount = amount;
        this.description = description;
        this.expiredAt = expiredAt;
        this.returnUrl = returnUrl;
        this.cancelUrl = cancelUrl;
    }

    public int getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(int orderCode) {
        this.orderCode = orderCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(long expiredAt) {
        this.expiredAt = expiredAt;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "orderCode=" + orderCode +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", expiredAt=" + expiredAt +
                ", returnUrl='" + returnUrl + '\'' +
                ", cancelUrl='" + cancelUrl + '\'' +
                '}';
    }

}

