package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.Shape;
import com.example.DiamondShopSystem.service.ShapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShapeController {
    @Autowired
    private ShapeService shapeService;

    @GetMapping("/customer/shapes")
    public List<Shape> getAllShapes() {
        return shapeService.findAllShapes();
    }

    @GetMapping("/adminsale/shapes/{id}")
    public Shape getShapeById(@PathVariable Long id) {
        return shapeService.findShapeById(id);
    }

    @PostMapping("/admin/shapes")
    public Shape createShape(@RequestBody Shape shape) {
        return shapeService.saveShape(shape);
    }

    @PutMapping("/admin/shapes/{id}")
    public Shape updateShape(@PathVariable Long id, @RequestBody Shape shape) {
        return shapeService.updateShape(id, shape);
    }

    @DeleteMapping("/admin/shapes/{id}")
    public void deleteShape(@PathVariable Long id) {
        shapeService.deleteShape(id);
    }
}
