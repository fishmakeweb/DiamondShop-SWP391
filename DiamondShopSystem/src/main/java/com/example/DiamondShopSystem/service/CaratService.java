package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.Carat;
import com.example.DiamondShopSystem.repository.CaratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaratService {
    @Autowired
    private CaratRepository caratRepository;

    public List<Carat> findAllCarats() {
        return caratRepository.findAll();
    }

    public Carat findCaratById(Long id) {
        Optional<Carat> carat = caratRepository.findById(id);
        return carat.orElse(null);
    }

    public Carat saveCarat(Carat carat) {
        return caratRepository.save(carat);
    }

    public Carat updateCarat(Long id, Carat newCarat) {
        return caratRepository.findById(id)
                .map(existingCarat -> {
                    existingCarat.setCarat(newCarat.getCarat());
                    return caratRepository.save(existingCarat);
                }).orElseGet(() -> {
                    newCarat.setCaratId(id);
                    return caratRepository.save(newCarat);
                });
    }

    public void deleteCarat(Long id) {
        caratRepository.deleteById(id);
    }
}
