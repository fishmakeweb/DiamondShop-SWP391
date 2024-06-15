package com.example.DiamondShopSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Diamond")
public class Diamond {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diamondId;

    @ManyToOne
    @JoinColumn(name = "shape_id")
    private Shape shape;

    @ManyToOne
    @JoinColumn(name = "measurement_id")
    private Measurement measurement;

    @ManyToOne
    @JoinColumn(name = "carat_id")
    private Carat carat;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "cut_id")
    private Cut cut;

    @ManyToOne
    @JoinColumn(name = "clarity_id")
    private Clarity clarity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gia_id", unique = true)
    private Gia gia;

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


    public Gia getGia() {
        return gia;
    }

    public void setGia(Gia gia) {
        this.gia = gia;
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
