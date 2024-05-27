package com.example.DiamondShopSystem.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Diamond")
public class Diamond {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diamondId;

    @ManyToOne
    @JoinColumn(name = "shapeId")
    private Shape shape;

    @ManyToOne
    @JoinColumn(name = "measurementId")
    private Measurement measurement;

    @ManyToOne
    @JoinColumn(name = "caratId")
    private Carat carat;

    @ManyToOne
    @JoinColumn(name = "colorId")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "cutId")
    private Cut cut;

    @ManyToOne
    @JoinColumn(name = "clarityId")
    private Clarity clarity;

    @ManyToOne
    @JoinColumn(name = "polishId")
    private Polish polish;

    @ManyToOne
    @JoinColumn(name = "symmetryId")
    private Symmetry symmetry;

    @ManyToOne
    @JoinColumn(name = "fluorescenceId")
    private Fluorescence fluorescence;

    @OneToOne
    @JoinColumn(name = "giaid", unique = true)
    private GIA giaId;

    private float price;
    private String img;

    public Long getDiamondId() {
        return diamondId;
    }

    public void setDiamondId(Long diamondId) {
        this.diamondId = diamondId;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public Carat getCarat() {
        return carat;
    }

    public void setCarat(Carat carat) {
        this.carat = carat;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Cut getCut() {
        return cut;
    }

    public void setCut(Cut cut) {
        this.cut = cut;
    }

    public Clarity getClarity() {
        return clarity;
    }

    public void setClarity(Clarity clarity) {
        this.clarity = clarity;
    }

    public Polish getPolish() {
        return polish;
    }

    public void setPolish(Polish polish) {
        this.polish = polish;
    }

    public Symmetry getSymmetry() {
        return symmetry;
    }

    public void setSymmetry(Symmetry symmetry) {
        this.symmetry = symmetry;
    }

    public Fluorescence getFluorescence() {
        return fluorescence;
    }

    public void setFluorescence(Fluorescence fluorescence) {
        this.fluorescence = fluorescence;
    }

    public GIA getGiaId() {
        return giaId;
    }

    public void setGiaId(GIA giaId) {
        this.giaId = giaId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
