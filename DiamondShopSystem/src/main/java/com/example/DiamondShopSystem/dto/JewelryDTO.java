package com.example.DiamondShopSystem.dto;

public class JewelryDTO {
    private Long jewelryId;
    private String name;
    private int price;
    private String img;

    // Constructor


    public JewelryDTO(Long jewelryId, String name, int price, String img) {
        this.jewelryId = jewelryId;
        this.name = name;
        this.price = price;
        this.img = img;
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
}
