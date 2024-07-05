package com.example.DiamondShopSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Shape")
public class Shape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shapeId;
    private String shapeDescription;
    private String shapeImg;
    // Getters and Setters
    public Long getShapeId() {
        return shapeId;
    }

    public void setShapeId(Long shapeId) {
        this.shapeId = shapeId;
    }

    public String getShapeDescription() {
        return shapeDescription;
    }

    public void setShapeDescription(String shapeDescription) {
        this.shapeDescription = shapeDescription;
    }

    public String getShapeImg() {
        return shapeImg;
    }

    public void setShapeImg(String shapeImg) {
        this.shapeImg = shapeImg;
    }
}
