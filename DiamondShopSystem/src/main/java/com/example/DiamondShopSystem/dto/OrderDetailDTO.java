package com.example.DiamondShopSystem.dto;

public class OrderDetailDTO {
    private String Name;
    private Long productId;
    private int quantity;
    private String imgUrl;
    // Getters and setters


    public OrderDetailDTO(String name, Long productId, int quantity, String imgUrl) {
        Name = name;
        this.productId = productId;
        this.quantity = quantity;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
