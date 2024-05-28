package com.example.DiamondShopSystem.model;

public class Criteria {
    //Diamond Criteria
    private Cut cut;
    private Fluorescence fluorescence;
    private Color color;
    private Clarity clarity;
    private Symmetry symmetry;
    private Carat carat;
    private Measurement measurement;
    private Shape shape;
    private Polish polish;
    //Jewelry Criteria
    private Category category;
    private Material material;
    private Gemstone gemstone;
    private Size size;

    //Getter, setters

    public Cut getCut() {
        return cut;
    }

    public void setCut(Cut cut) {
        this.cut = cut;
    }

    public Fluorescence getFluorescence() {
        return fluorescence;
    }

    public void setFluorescence(Fluorescence fluorescence) {
        this.fluorescence = fluorescence;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Clarity getClarity() {
        return clarity;
    }

    public void setClarity(Clarity clarity) {
        this.clarity = clarity;
    }

    public Symmetry getSymmetry() {
        return symmetry;
    }

    public void setSymmetry(Symmetry symmetry) {
        this.symmetry = symmetry;
    }

    public Carat getCarat() {
        return carat;
    }

    public void setCarat(Carat carat) {
        this.carat = carat;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Polish getPolish() {
        return polish;
    }

    public void setPolish(Polish polish) {
        this.polish = polish;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Gemstone getGemstone() {
        return gemstone;
    }

    public void setGemstone(Gemstone gemstone) {
        this.gemstone = gemstone;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
