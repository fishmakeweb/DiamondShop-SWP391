package com.example.DiamondShopSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Gemstone")
public class Gemstone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gemstoneId;
    private String gemstoneName;

    // Getters and Setters
    public Long getGemstoneId() {
        return gemstoneId;
    }

    public void setGemstoneId(Long gemstoneId) {
        this.gemstoneId = gemstoneId;
    }

    public String getGemstoneName() {
        return gemstoneName;
    }

    public void setGemstoneName(String gemstoneName) {
        this.gemstoneName = gemstoneName;
    }
}
