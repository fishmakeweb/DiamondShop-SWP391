package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.Symmetry;
import com.example.DiamondShopSystem.repository.SymmetryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SymmetryService {
    @Autowired
    private SymmetryRepository symmetryRepository;

    public List<Symmetry> findAllSymmetries() {
        return symmetryRepository.findAll();
    }

    public Symmetry findSymmetryById(Long id) {
        Optional<Symmetry> symmetry = symmetryRepository.findById(id);
        return symmetry.orElse(null);
    }

    public Symmetry saveSymmetry(Symmetry symmetry) {
        return symmetryRepository.save(symmetry);
    }

    public Symmetry updateSymmetry(Long id, Symmetry newSymmetry) {
        return symmetryRepository.findById(id)
                .map(existingSymmetry -> {
                    existingSymmetry.setSymmetryDescription(newSymmetry.getSymmetryDescription());
                    return symmetryRepository.save(existingSymmetry);
                }).orElseGet(() -> {
                    newSymmetry.setSymmetryId(id);
                    return symmetryRepository.save(newSymmetry);
                });
    }

    public void deleteSymmetry(Long id) {
        symmetryRepository.deleteById(id);
    }
}
