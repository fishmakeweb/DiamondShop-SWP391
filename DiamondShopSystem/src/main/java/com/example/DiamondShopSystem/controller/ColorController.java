package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.Color;
import com.example.DiamondShopSystem.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ColorController {
    @Autowired
    private ColorService colorService;

    @GetMapping("/adminsale/colors")
    public List<Color> getAllColors() {
        return colorService.findAllColors();
    }

    @GetMapping("/adminsale/colors/{id}")
    public Color getColorById(@PathVariable Long id) {
        return colorService.findColorById(id);
    }

    @PostMapping("/admin/colors")
    public Color createColor(@RequestBody Color color) {
        return colorService.saveColor(color);
    }

    @PutMapping("/admin/colors/{id}")
    public Color updateColor(@PathVariable Long id, @RequestBody Color color) {
        return colorService.updateColor(id, color);
    }

    @DeleteMapping("/admin/colors/{id}")
    public void deleteColor(@PathVariable Long id) {
        colorService.deleteColor(id);
    }
}
