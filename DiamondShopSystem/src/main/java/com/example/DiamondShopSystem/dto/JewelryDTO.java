package com.example.DiamondShopSystem.dto;

public class JewelryDTO {
    private Long productId;
    private Long jewelryId;
    private String name;
    private float price;
    private String img;

    // Constructor
    public JewelryDTO(Long productId, Long jewelryId, String name, float price, String img) {
        this.productId = productId;
        this.jewelryId = jewelryId;
        this.name = name;
        this.price = price;
        this.img = img;
    }

    // Getters and Setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getJewelryId() {
        return jewelryId;
    }

    public void setJewelryId(Long jewelryId) {
        this.jewelryId = jewelryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
