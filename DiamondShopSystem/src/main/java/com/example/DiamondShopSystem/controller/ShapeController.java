package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.service.ShapeService;
import com.example.DiamondShopSystem.model.Shape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/shapes")
@CrossOrigin(origins = "http://localhost:3000")
public class ShapeController {
    @Autowired
    private ShapeService shapeService;

    @GetMapping
    public List<Shape> getAllShapes() {
        return shapeService.findAllShapes();
    }

    @GetMapping("/{id}")
    public Shape getShapeById(@PathVariable Long id) {
        return shapeService.findShapeById(id);
    }

    @PostMapping
    public Shape createShape(@RequestBody Shape shape) {
        return shapeService.saveShape(shape);
    }

    @PutMapping("/{id}")
    public Shape updateShape(@PathVariable Long id, @RequestBody Shape shape) {
        return shapeService.updateShape(id, shape);
    }

    @DeleteMapping("/{id}")
    public void deleteShape(@PathVariable Long id) {
        shapeService.deleteShape(id);
    }
}
