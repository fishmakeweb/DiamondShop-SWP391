package com.example.DiamondShopSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "diamond_id")
    private Diamond diamond;

    @ManyToOne
    @JoinColumn(name = "jewelry_id")
    private Jewelry jewelry;

    // Getters and Setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Diamond getDiamond() {
        return diamond;
    }

    public void setDiamond(Diamond diamond) {
        this.diamond = diamond;
    }

    public Jewelry getJewelry() {
        return jewelry;
    }

    public void setJewelry(Jewelry jewelry) {
        this.jewelry = jewelry;
    }
}
