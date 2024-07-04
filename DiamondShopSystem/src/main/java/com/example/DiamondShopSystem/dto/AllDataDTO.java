package com.example.DiamondShopSystem.dto;

import com.example.DiamondShopSystem.model.Category;
import com.example.DiamondShopSystem.model.Material;
import com.example.DiamondShopSystem.model.Shape;
import com.example.DiamondShopSystem.model.Size;

import java.util.List;

public class AllDataDTO {
    private List<Category> categories;
    private List<Material> materials;
    private List<Size> sizes;
    private List<Shape> shapes;
    public AllDataDTO(List<Category> categories, List<Material> materials, List<Size> sizes, List<Shape> shapes) {
        this.categories = categories;
        this.materials = materials;
        this.sizes = sizes;
        this.shapes = shapes;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public List<Size> getSizes() {
        return sizes;
    }

    public void setSizes(List<Size> sizes) {
        this.sizes = sizes;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }
}
