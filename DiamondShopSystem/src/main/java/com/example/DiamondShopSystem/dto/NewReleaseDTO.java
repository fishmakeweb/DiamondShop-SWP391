package com.example.DiamondShopSystem.dto;

import java.util.Date;

public class NewReleaseDTO {
    private Long productId;
    private String name;
    private int price;
    private String img;
    private Date date;

    // Constructor
    public NewReleaseDTO(Long productId, String name, int price, String img, Date date) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.img = img;
        this.date = date;
    }

    // Getters and Setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
