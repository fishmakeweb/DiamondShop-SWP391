package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.service.SymmetryService;
import com.example.DiamondShopSystem.model.Symmetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/symmetries")
@CrossOrigin(origins = "http://localhost:3000")
public class SymmetryController {
    @Autowired
    private SymmetryService symmetryService;

    @GetMapping
    public List<Symmetry> getAllSymmetries() {
        return symmetryService.findAllSymmetries();
    }

    @GetMapping("/{id}")
    public Symmetry getSymmetryById(@PathVariable Long id) {
        return symmetryService.findSymmetryById(id);
    }

    @PostMapping
    public Symmetry createSymmetry(@RequestBody Symmetry symmetry) {
        return symmetryService.saveSymmetry(symmetry);
    }

    @PutMapping("/{id}")
    public Symmetry updateSymmetry(@PathVariable Long id, @RequestBody Symmetry symmetry) {
        return symmetryService.updateSymmetry(id, symmetry);
    }

    @DeleteMapping("/{id}")
    public void deleteSymmetry(@PathVariable Long id) {
        symmetryService.deleteSymmetry(id);
    }
}
