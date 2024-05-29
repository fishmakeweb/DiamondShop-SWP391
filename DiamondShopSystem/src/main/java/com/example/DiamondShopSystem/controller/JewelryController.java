package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.service.JewelryService;
import com.example.DiamondShopSystem.model.Jewelry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/jewelry")
@CrossOrigin(origins = "http://localhost:3000")
public class JewelryController {
    @Autowired
    private JewelryService jewelryService;

    @GetMapping
    public List<Jewelry> getAllJewelry() {
        return jewelryService.findAllJewelry();
    }

    @GetMapping("/{id}")
    public Jewelry getJewelryById(@PathVariable Long id) {
        return jewelryService.findJewelryById(id);
    }

    @PostMapping
    public Jewelry createJewelry(@RequestBody Jewelry jewelry) {
        return jewelryService.saveJewelry(jewelry);
    }

    @PutMapping("/{id}")
    public Jewelry updateJewelry(@PathVariable Long id, @RequestBody Jewelry jewelry) {
        return jewelryService.updateJewelry(id, jewelry);
    }

    @DeleteMapping("/{id}")
    public void deleteJewelry(@PathVariable Long id) {
        jewelryService.deleteJewelry(id);
    }

    @GetMapping("/categories/{categoryId}")
    public List<Jewelry> getJewelryByCategory(@PathVariable Long categoryId) {
        return jewelryService.findAllByCategoryId(categoryId);
    }

    @GetMapping("/gemstones/{gemstoneId}")
    public List<Jewelry> getJewelryByGemstone(@PathVariable Long gemstoneId) {
        return jewelryService.findAllByGemStoneId(gemstoneId);
    }

    @GetMapping("/price/range")
    public List<Jewelry> getJewelryByPriceRange(@RequestParam("min") float minPrice, @RequestParam("max") float maxPrice) {
        return jewelryService.findAllByPriceRange(minPrice, maxPrice);
    }
}
