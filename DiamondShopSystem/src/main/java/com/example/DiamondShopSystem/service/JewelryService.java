package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.Jewelry;
import com.example.DiamondShopSystem.repository.JewelryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JewelryService {
    @Autowired
    private JewelryRepository jewelryRepository;

    public List<Jewelry> findAllJewelry() {
        return jewelryRepository.findAll();
    }

    public Jewelry findJewelryById(Long id) {
        Optional<Jewelry> jewelry = jewelryRepository.findById(id);
        return jewelry.orElse(null);
    }

    public Jewelry saveJewelry(Jewelry jewelry) {
        return jewelryRepository.save(jewelry);
    }

    public Jewelry updateJewelry(Long id, Jewelry newJewelry) {
        return jewelryRepository.findById(id)
                .map(existingJewelry -> {
                    existingJewelry.setName(newJewelry.getName());
                    existingJewelry.setImg(newJewelry.getImg());
                    existingJewelry.setPrice(newJewelry.getPrice());
                    existingJewelry.setDiamond(newJewelry.getDiamond());
                    existingJewelry.setMaterial(newJewelry.getMaterial());
                    existingJewelry.setCategory(newJewelry.getCategory());
                    existingJewelry.setGemstone(newJewelry.getGemstone());
                    existingJewelry.setSize(newJewelry.getSize());
                    return jewelryRepository.save(existingJewelry);
                }).orElseGet(() -> {
                    newJewelry.setJewelryId(id);
                    return jewelryRepository.save(newJewelry);
                });
    }

    public void deleteJewelry(Long id) {
        jewelryRepository.deleteById(id);
    }
    public List<Jewelry> findAllByCategoryId(Long categoryId) {
        return jewelryRepository.findAll().stream()
                .filter(jewelry -> jewelry.getCategory().getCategoryId().equals(categoryId))
                .collect(Collectors.toList());
    }

    public List<Jewelry> findAllByGemStoneId(Long gemstoneId) {
        return jewelryRepository.findAll().stream()
                .filter(jewelry -> jewelry.getGemstone() != null && jewelry.getGemstone().getGemstoneId().equals(gemstoneId))
                .collect(Collectors.toList());
    }
}


