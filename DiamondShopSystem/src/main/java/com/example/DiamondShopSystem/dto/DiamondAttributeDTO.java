package com.example.DiamondShopSystem.dto;

import com.example.DiamondShopSystem.model.*;

import java.util.List;

public class DiamondAttributeDTO {
    private List<Shape> shapes;
    private List<Measurement> measurements;
    private List<Color> colors;
    private List<Cut> cuts;
    private List<Carat> carats;
    private List<Clarity> clarities;

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    public List<Color> getColors() {
        return colors;
    }

    public void setColors(List<Color> colors) {
        this.colors = colors;
    }

    public List<Cut> getCuts() {
        return cuts;
    }

    public void setCuts(List<Cut> cuts) {
        this.cuts = cuts;
    }

    public List<Carat> getCarats() {
        return carats;
    }

    public void setCarats(List<Carat> carats) {
        this.carats = carats;
    }

    public List<Clarity> getClarities() {
        return clarities;
    }

    public void setClarities(List<Clarity> clarities) {
        this.clarities = clarities;
    }

    // Getters and setters
}
