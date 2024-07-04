package com.example.DiamondShopSystem.dto;

import com.example.DiamondShopSystem.model.*;

public class CustomJewelryRequestDTO {
    private Long customJewelryId;
    private Diamond diamond;
    private Material material;
    private Category category;
    private Size size;
    private Shape shape;
    private String img;
    private String note;

    // Getters and Setters

    public Long getCustomJewelryId() {
        return customJewelryId;
    }

    public void setCustomJewelryId(Long customJewelryId) {
        this.customJewelryId = customJewelryId;
    }

    public Diamond getDiamond() {
        return diamond;
    }

    public void setDiamond(Diamond diamond) {
        this.diamond = diamond;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
