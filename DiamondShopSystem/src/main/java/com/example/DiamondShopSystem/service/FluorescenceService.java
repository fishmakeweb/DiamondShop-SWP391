package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.Fluorescence;
import com.example.DiamondShopSystem.repository.FluorescenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FluorescenceService {
    @Autowired
    private FluorescenceRepository fluorescenceRepository;

    public List<Fluorescence> findAllFluorescences() {
        return fluorescenceRepository.findAll();
    }

    public Fluorescence findFluorescenceById(Long id) {
        Optional<Fluorescence> fluorescence = fluorescenceRepository.findById(id);
        return fluorescence.orElse(null);
    }

    public Fluorescence saveFluorescence(Fluorescence fluorescence) {
        return fluorescenceRepository.save(fluorescence);
    }

    public Fluorescence updateFluorescence(Long id, Fluorescence newFluorescence) {
        return fluorescenceRepository.findById(id)
                .map(existingFluorescence -> {
                    existingFluorescence.setFluorescenceDescription(newFluorescence.getFluorescenceDescription());
                    return fluorescenceRepository.save(existingFluorescence);
                }).orElseGet(() -> {
                    newFluorescence.setFluorescenceId(id);
                    return fluorescenceRepository.save(newFluorescence);
                });
    }

    public void deleteFluorescence(Long id) {
        fluorescenceRepository.deleteById(id);
    }
}
