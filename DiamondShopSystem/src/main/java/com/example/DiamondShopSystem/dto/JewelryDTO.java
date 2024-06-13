package com.example.DiamondShopSystem.dto;

public class JewelryDTO {
    private Long jewelryId;
    private String name;
    private String img;
    private float price;

    public JewelryDTO() {
    }

    public JewelryDTO(Long jewelryId, String name, String img, float price) {
        this.jewelryId = jewelryId;
        this.name = name;
        this.img = img;
        this.price = price;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
