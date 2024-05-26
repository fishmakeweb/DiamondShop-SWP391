package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.Material;
import com.example.DiamondShopSystem.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {
    @Autowired
    private MaterialRepository materialRepository;

    public List<Material> findAllMaterials() {
        return materialRepository.findAll();
    }

    public Material findMaterialById(Long id) {
        Optional<Material> material = materialRepository.findById(id);
        return material.orElse(null);
    }

    public Material saveMaterial(Material material) {
        return materialRepository.save(material);
    }

    public Material updateMaterial(Long id, Material newMaterial) {
        return materialRepository.findById(id)
                .map(existingMaterial -> {
                    existingMaterial.setMaterialName(newMaterial.getMaterialName());
                    return materialRepository.save(existingMaterial);
                }).orElseGet(() -> {
                    newMaterial.setMaterialId(id);
                    return materialRepository.save(newMaterial);
                });
    }

    public void deleteMaterial(Long id) {
        materialRepository.deleteById(id);
    }
}
