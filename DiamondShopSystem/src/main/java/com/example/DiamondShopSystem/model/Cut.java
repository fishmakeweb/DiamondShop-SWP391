package com.example.DiamondShopSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Cut")
public class Cut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cutId;
    private String cutDescription;

    // Getters and Setters
    public Long getCutId() {
        return cutId;
    }

    public void setCutId(Long cutId) {
        this.cutId = cutId;
    }

    public String getCutDescription() {
        return cutDescription;
    }

    public void setCutDescription(String cutDescription) {
        this.cutDescription = cutDescription;
    }
}
