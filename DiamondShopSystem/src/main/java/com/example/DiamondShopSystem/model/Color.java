package com.example.DiamondShopSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Color")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long colorId;
    private String colorDescription;

    // Getters and Setters
    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
    }

    public String getColorDescription() {
        return colorDescription;
    }

    public void setColorDescription(String colorDescription) {
        this.colorDescription = colorDescription;
    }
}
