package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.Material;
import com.example.DiamondShopSystem.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @GetMapping("/materials")
    public List<Material> getAllMaterials() {
        return materialService.findAllMaterials();
    }

    @GetMapping("/materials/{id}")
    public Material getMaterialById(@PathVariable Long id) {
        return materialService.findMaterialById(id);
    }

    @PostMapping("/secure/materials")
    public Material createMaterial(@RequestBody Material material) {
        return materialService.saveMaterial(material);
    }

    @PutMapping("/secure/materials/{id}")
    public Material updateMaterial(@PathVariable Long id, @RequestBody Material material) {
        return materialService.updateMaterial(id, material);
    }

    @DeleteMapping("/secure/materials/{id}")
    public void deleteMaterial(@PathVariable Long id) {
        materialService.deleteMaterial(id);
    }
}
