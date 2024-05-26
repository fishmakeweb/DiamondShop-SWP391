package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.Diamond;
import com.example.DiamondShopSystem.repository.DiamondRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DiamondService {
    @Autowired
    private DiamondRepository diamondRepository;

    public List<Diamond> findAllDiamonds() {
        return diamondRepository.findAll();
    }

    public Diamond findDiamondById(Long id) {
        Optional<Diamond> diamond = diamondRepository.findById(id);
        return diamond.orElse(null);
    }

    public Diamond saveDiamond(Diamond diamond) {
        return diamondRepository.save(diamond);
    }

    public Diamond updateDiamond(Long id, Diamond newDiamond) {
        return diamondRepository.findById(id)
                .map(existingDiamond -> {
                    existingDiamond.setShape(newDiamond.getShape());
                    existingDiamond.setMeasurement(newDiamond.getMeasurement());
                    existingDiamond.setCarat(newDiamond.getCarat());
                    existingDiamond.setColor(newDiamond.getColor());
                    existingDiamond.setCut(newDiamond.getCut());
                    existingDiamond.setClarity(newDiamond.getClarity());
                    existingDiamond.setPolish(newDiamond.getPolish());
                    existingDiamond.setSymmetry(newDiamond.getSymmetry());
                    existingDiamond.setFluorescence(newDiamond.getFluorescence());
                    existingDiamond.setPrice(newDiamond.getPrice());
                    existingDiamond.setImg(newDiamond.getImg());
                    return diamondRepository.save(existingDiamond);
                }).orElseGet(() -> {
                    newDiamond.setDiamondId(id);
                    return diamondRepository.save(newDiamond);
                });
    }

    public void deleteDiamond(Long id) {
        diamondRepository.deleteById(id);
    }
}
