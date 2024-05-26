package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.Polish;
import com.example.DiamondShopSystem.repository.PolishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PolishService {
    @Autowired
    private PolishRepository polishRepository;

    public List<Polish> findAllPolishes() {
        return polishRepository.findAll();
    }

    public Polish findPolishById(Long id) {
        Optional<Polish> polish = polishRepository.findById(id);
        return polish.orElse(null);
    }

    public Polish savePolish(Polish polish) {
        return polishRepository.save(polish);
    }

    public Polish updatePolish(Long id, Polish newPolish) {
        return polishRepository.findById(id)
                .map(existingPolish -> {
                    existingPolish.setPolishDescription(newPolish.getPolishDescription());
                    return polishRepository.save(existingPolish);
                }).orElseGet(() -> {
                    newPolish.setPolishId(id);
                    return polishRepository.save(newPolish);
                });
    }

    public void deletePolish(Long id) {
        polishRepository.deleteById(id);
    }
}
