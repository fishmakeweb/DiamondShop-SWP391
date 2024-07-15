package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.Clarity;
import com.example.DiamondShopSystem.repository.ClarityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClarityService {
    @Autowired
    private ClarityRepository clarityRepository;

    public List<Clarity> findAllClarities() {
        return clarityRepository.findAll();
    }

    public Clarity findClarityById(Long id) {
        Optional<Clarity> clarity = clarityRepository.findById(id);
        return clarity.orElse(null);
    }

    public Clarity saveClarity(Clarity clarity) {
        return clarityRepository.save(clarity);
    }

    public Clarity updateClarity(Long id, Clarity newClarity) {
        return clarityRepository.findById(id)
                .map(existingClarity -> {
                    existingClarity.setClarityDescription(newClarity.getClarityDescription());
                    return clarityRepository.save(existingClarity);
                }).orElseGet(() -> {
                    newClarity.setClarityId(id);
                    return clarityRepository.save(newClarity);
                });
    }

    public void deleteClarity(Long id) {
        clarityRepository.deleteById(id);
    }
}
