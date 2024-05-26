package com.example.DiamondShopSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Clarity")
public class Clarity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clarityId;
    private String clarityDescription;

    // Getters and Setters
    public Long getClarityId() {
        return clarityId;
    }

    public void setClarityId(Long clarityId) {
        this.clarityId = clarityId;
    }

    public String getClarityDescription() {
        return clarityDescription;
    }

    public void setClarityDescription(String clarityDescription) {
        this.clarityDescription = clarityDescription;
    }
}
