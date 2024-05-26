package com.example.DiamondShopSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Fluorescence")
public class Fluorescence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fluorescenceId;
    private String fluorescenceDescription;

    // Getters and Setters
    public Long getFluorescenceId() {
        return fluorescenceId;
    }

    public void setFluorescenceId(Long fluorescenceId) {
        this.fluorescenceId = fluorescenceId;
    }

    public String getFluorescenceDescription() {
        return fluorescenceDescription;
    }

    public void setFluorescenceDescription(String fluorescenceDescription) {
        this.fluorescenceDescription = fluorescenceDescription;
    }
}
