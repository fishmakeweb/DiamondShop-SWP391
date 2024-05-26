package com.example.DiamondShopSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Carat")
public class Carat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long caratId;
    private double carat;

    // Getters and Setters
    public Long getCaratId() {
        return caratId;
    }

    public void setCaratId(Long caratId) {
        this.caratId = caratId;
    }

    public double getCarat() {
        return carat;
    }

    public void setCarat(double carat) {
        this.carat = carat;
    }
}
