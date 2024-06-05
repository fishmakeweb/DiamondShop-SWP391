package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.Symmetry;
import com.example.DiamondShopSystem.service.SymmetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class SymmetryController {
    @Autowired
    private SymmetryService symmetryService;

    @GetMapping("/symmetries")
    public List<Symmetry> getAllSymmetries() {
        return symmetryService.findAllSymmetries();
    }

    @GetMapping("/symmetries/{id}")
    public Symmetry getSymmetryById(@PathVariable Long id) {
        return symmetryService.findSymmetryById(id);
    }

    @PostMapping("/secure/symmetries")
    public Symmetry createSymmetry(@RequestBody Symmetry symmetry) {
        return symmetryService.saveSymmetry(symmetry);
    }

    @PutMapping("/secure/symmetries/{id}")
    public Symmetry updateSymmetry(@PathVariable Long id, @RequestBody Symmetry symmetry) {
        return symmetryService.updateSymmetry(id, symmetry);
    }

    @DeleteMapping("/secure/symmetries/{id}")
    public void deleteSymmetry(@PathVariable Long id) {
        symmetryService.deleteSymmetry(id);
    }
}
