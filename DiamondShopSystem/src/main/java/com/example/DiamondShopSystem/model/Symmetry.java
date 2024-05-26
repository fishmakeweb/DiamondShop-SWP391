package com.example.DiamondShopSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Symmetry")
public class Symmetry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long symmetryId;
    private String symmetryDescription;

    // Getters and Setters
    public Long getSymmetryId() {
        return symmetryId;
    }

    public void setSymmetryId(Long symmetryId) {
        this.symmetryId = symmetryId;
    }

    public String getSymmetryDescription() {
        return symmetryDescription;
    }

    public void setSymmetryDescription(String symmetryDescription) {
        this.symmetryDescription = symmetryDescription;
    }
}
