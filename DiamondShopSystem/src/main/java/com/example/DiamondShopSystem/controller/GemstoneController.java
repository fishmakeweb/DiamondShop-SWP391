package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.Gemstone;
import com.example.DiamondShopSystem.service.GemstoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class GemstoneController {
    @Autowired
    private GemstoneService gemstoneService;

    @GetMapping("/gemstones")
    public List<Gemstone> getAllGemstones() {
        return gemstoneService.findAllGemstones();
    }

    @GetMapping("/gemstones/{id}")
    public Gemstone getGemstoneById(@PathVariable Long id) {
        return gemstoneService.findGemstoneById(id);
    }

    @PostMapping("/secure/gemstones")
    public Gemstone createGemstone(@RequestBody Gemstone gemstone) {
        return gemstoneService.saveGemstone(gemstone);
    }

    @PutMapping("/secure/gemstones/{id}")
    public Gemstone updateGemstone(@PathVariable Long id, @RequestBody Gemstone gemstone) {
        return gemstoneService.updateGemstone(id, gemstone);
    }

    @DeleteMapping("/secure/gemstones/{id}")
    public void deleteGemstone(@PathVariable Long id) {
        gemstoneService.deleteGemstone(id);
    }
}
