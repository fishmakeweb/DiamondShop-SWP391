package com.example.DiamondShopSystem.model;


import jakarta.persistence.*;

@Entity
@Table(name = "CustomJewelry")
public class CustomJewelry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customJewelryId;

    @ManyToOne
    @JoinColumn(name = "diamond_id")
    private Diamond diamond;

    @ManyToOne
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "size_id", nullable = false)
    private Size size;

    @ManyToOne
    @JoinColumn(name = "shape_id")
    private Shape shape;
    private float price;
    private String note;

    public CustomJewelry() {
    }

    public CustomJewelry(Long customJewelryId, Diamond diamond, Material material, Category category, Size size, Shape shape, String img, float price, String note) {
        this.customJewelryId = customJewelryId;
        this.diamond = diamond;
        this.material = material;
        this.category = category;
        this.size = size;
        this.shape = shape;
        this.price = price;
        this.note = note;
    }

    public Long getCustomJewelryId() {
        return customJewelryId;
    }

    public void setCustomJewelryId(Long customJewelryId) {
        this.customJewelryId = customJewelryId;
    }

    public Diamond getDiamond() {
        return diamond;
    }

    public void setDiamond(Diamond diamond) {
        this.diamond = diamond;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
