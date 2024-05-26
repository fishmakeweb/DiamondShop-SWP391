package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.service.FluorescenceService;
import com.example.DiamondShopSystem.model.Fluorescence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/fluorescences")
@CrossOrigin(origins = "http://localhost:3000")
public class FluorescenceController {
    @Autowired
    private FluorescenceService fluorescenceService;

    @GetMapping
    public List<Fluorescence> getAllFluorescences() {
        return fluorescenceService.findAllFluorescences();
    }

    @GetMapping("/{id}")
    public Fluorescence getFluorescenceById(@PathVariable Long id) {
        return fluorescenceService.findFluorescenceById(id);
    }

    @PostMapping
    public Fluorescence createFluorescence(@RequestBody Fluorescence fluorescence) {
        return fluorescenceService.saveFluorescence(fluorescence);
    }

    @PutMapping("/{id}")
    public Fluorescence updateFluorescence(@PathVariable Long id, @RequestBody Fluorescence fluorescence) {
        return fluorescenceService.updateFluorescence(id, fluorescence);
    }

    @DeleteMapping("/{id}")
    public void deleteFluorescence(@PathVariable Long id) {
        fluorescenceService.deleteFluorescence(id);
    }
}
