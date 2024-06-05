package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.Fluorescence;
import com.example.DiamondShopSystem.service.FluorescenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class FluorescenceController {
    @Autowired
    private FluorescenceService fluorescenceService;

    @GetMapping("/fluorescences")
    public List<Fluorescence> getAllFluorescences() {
        return fluorescenceService.findAllFluorescences();
    }

    @GetMapping("/fluorescences/{id}")
    public Fluorescence getFluorescenceById(@PathVariable Long id) {
        return fluorescenceService.findFluorescenceById(id);
    }

    @PostMapping("/secure/fluorescences")
    public Fluorescence createFluorescence(@RequestBody Fluorescence fluorescence) {
        return fluorescenceService.saveFluorescence(fluorescence);
    }

    @PutMapping("/secure/fluorescences/{id}")
    public Fluorescence updateFluorescence(@PathVariable Long id, @RequestBody Fluorescence fluorescence) {
        return fluorescenceService.updateFluorescence(id, fluorescence);
    }

    @DeleteMapping("/secure/fluorescences/{id}")
    public void deleteFluorescence(@PathVariable Long id) {
        fluorescenceService.deleteFluorescence(id);
    }
}
