package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.service.GemstoneService;
import com.example.DiamondShopSystem.model.Gemstone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/gemstones")
@CrossOrigin(origins = "http://localhost:3000")
public class GemstoneController {
    @Autowired
    private GemstoneService gemstoneService;

    @GetMapping
    public List<Gemstone> getAllGemstones() {
        return gemstoneService.findAllGemstones();
    }

    @GetMapping("/{id}")
    public Gemstone getGemstoneById(@PathVariable Long id) {
        return gemstoneService.findGemstoneById(id);
    }

    @PostMapping
    public Gemstone createGemstone(@RequestBody Gemstone gemstone) {
        return gemstoneService.saveGemstone(gemstone);
    }

    @PutMapping("/{id}")
    public Gemstone updateGemstone(@PathVariable Long id, @RequestBody Gemstone gemstone) {
        return gemstoneService.updateGemstone(id, gemstone);
    }

    @DeleteMapping("/{id}")
    public void deleteGemstone(@PathVariable Long id) {
        gemstoneService.deleteGemstone(id);
    }
}
