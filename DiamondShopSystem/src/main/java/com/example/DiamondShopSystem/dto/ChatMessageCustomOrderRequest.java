package com.example.DiamondShopSystem.dto;

public class ChatMessageCustomOrderRequest {
    private String username;
    private Long customOrderId;
    private String message; // Instead of message

    // Getters and Setters


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getCustomOrderId() {
        return customOrderId;
    }

    public void setCustomOrderId(Long orderId) {
        this.customOrderId = orderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
