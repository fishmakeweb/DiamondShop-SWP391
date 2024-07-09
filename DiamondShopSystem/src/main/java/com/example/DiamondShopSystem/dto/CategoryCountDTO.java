package com.example.DiamondShopSystem.dto;

public class CategoryCountDTO {
    private Long categoryId;
    private String categoryName;
    private Long orderCount;
    private String categoryImg;

    public CategoryCountDTO(Long categoryId, String categoryName,Long orderCount, String categoryImg) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.orderCount = orderCount;
        this.categoryImg = categoryImg;
    }

    public String getCategoryImg() {
        return categoryImg;
    }

    public void setCategoryImg(String categoryImg) {
        this.categoryImg = categoryImg;
    }

    // Getters and setters (you can also make fields public if you prefer)
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }
}