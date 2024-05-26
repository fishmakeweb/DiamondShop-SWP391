package com.example.DiamondShopSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Polish")
public class Polish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long polishId;
    private String polishDescription;

    // Getters and Setters
    public Long getPolishId() {
        return polishId;
    }

    public void setPolishId(Long polishId) {
        this.polishId = polishId;
    }

    public String getPolishDescription() {
        return polishDescription;
    }

    public void setPolishDescription(String polishDescription) {
        this.polishDescription = polishDescription;
    }
}
