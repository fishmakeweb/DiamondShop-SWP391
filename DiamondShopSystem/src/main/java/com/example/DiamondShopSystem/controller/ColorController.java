package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.service.ColorService;
import com.example.DiamondShopSystem.model.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/colors")
@CrossOrigin(origins = "http://localhost:3000")
public class ColorController {
    @Autowired
    private ColorService colorService;

    @GetMapping
    public List<Color> getAllColors() {
        return colorService.findAllColors();
    }

    @GetMapping("/{id}")
    public Color getColorById(@PathVariable Long id) {
        return colorService.findColorById(id);
    }

    @PostMapping
    public Color createColor(@RequestBody Color color) {
        return colorService.saveColor(color);
    }

    @PutMapping("/{id}")
    public Color updateColor(@PathVariable Long id, @RequestBody Color color) {
        return colorService.updateColor(id, color);
    }

    @DeleteMapping("/{id}")
    public void deleteColor(@PathVariable Long id) {
        colorService.deleteColor(id);
    }
}
