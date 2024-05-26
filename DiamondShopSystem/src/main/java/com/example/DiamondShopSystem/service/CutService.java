package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.repository.CutRepository;
import com.example.DiamondShopSystem.model.Cut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CutService {
    @Autowired
    private CutRepository cutRepository;

    public List<Cut> findAllCuts() {
        return cutRepository.findAll();
    }

    public Cut findCutById(Long id) {
        Optional<Cut> cut = cutRepository.findById(id);
        return cut.orElse(null);
    }

    public Cut saveCut(Cut cut) {
        return cutRepository.save(cut);
    }

    public Cut updateCut(Long id, Cut newCut) {
        return cutRepository.findById(id)
                .map(existingCut -> {
                    existingCut.setCutDescription(newCut.getCutDescription());
                    return cutRepository.save(existingCut);
                }).orElseGet(() -> {
                    newCut.setCutId(id);
                    return cutRepository.save(newCut);
                });
    }

    public void deleteCut(Long id) {
        cutRepository.deleteById(id);
    }
}
