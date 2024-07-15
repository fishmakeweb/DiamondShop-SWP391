package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.Shape;
import com.example.DiamondShopSystem.repository.ShapeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShapeService {
    @Autowired
    private ShapeRepository shapeRepository;

    public List<Shape> findAllShapes() {
        return shapeRepository.findAll();
    }

    public Shape findShapeById(Long id) {
        Optional<Shape> shape = shapeRepository.findById(id);
        return shape.orElse(null);
    }

    public Shape saveShape(Shape shape) {
        return shapeRepository.save(shape);
    }

    public Shape updateShape(Long id, Shape newShape) {
        return shapeRepository.findById(id)
                .map(existingShape -> {
                    existingShape.setShapeDescription(newShape.getShapeDescription());
                    return shapeRepository.save(existingShape);
                }).orElseGet(() -> {
                    newShape.setShapeId(id);
                    return shapeRepository.save(newShape);
                });
    }

    public void deleteShape(Long id) {
        shapeRepository.deleteById(id);
    }
}
