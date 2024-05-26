package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.Gemstone;
import com.example.DiamondShopSystem.repository.GemstoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GemstoneService {
    @Autowired
    private GemstoneRepository gemstoneRepository;

    public List<Gemstone> findAllGemstones() {
        return gemstoneRepository.findAll();
    }

    public Gemstone findGemstoneById(Long id) {
        Optional<Gemstone> gemstone = gemstoneRepository.findById(id);
        return gemstone.orElse(null);
    }

    public Gemstone saveGemstone(Gemstone gemstone) {
        return gemstoneRepository.save(gemstone);
    }

    public Gemstone updateGemstone(Long id, Gemstone newGemstone) {
        return gemstoneRepository.findById(id)
                .map(existingGemstone -> {
                    existingGemstone.setGemstoneName(newGemstone.getGemstoneName());
                    return gemstoneRepository.save(existingGemstone);
                }).orElseGet(() -> {
                    newGemstone.setGemstoneId(id);
                    return gemstoneRepository.save(newGemstone);
                });
    }

    public void deleteGemstone(Long id) {
        gemstoneRepository.deleteById(id);
    }
}
