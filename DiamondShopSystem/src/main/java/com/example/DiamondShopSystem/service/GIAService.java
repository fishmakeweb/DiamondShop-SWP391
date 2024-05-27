package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.GIA;
import com.example.DiamondShopSystem.repository.GIARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GIAService {
    @Autowired
    private GIARepository giaRepository;

    public List<GIA> findAllGIAs() {
        return giaRepository.findAll();
    }

    public GIA findGIAById(Long id) {
        Optional<GIA> gia = giaRepository.findById(id);
        return gia.orElse(null);
    }

    public GIA saveGIA(GIA gia) {
        return giaRepository.save(gia);
    }

    public GIA updateGIA(Long id, GIA newGIA) {
        return giaRepository.findById(id)
                .map(existingGIA -> {
                    existingGIA.setIssueDate(newGIA.getIssueDate());
                    return giaRepository.save(existingGIA);
                }).orElseGet(() -> {
                    newGIA.setGIAId(id);
                    return giaRepository.save(newGIA);
                });
    }

    public void deleteGIA(Long id) {
        giaRepository.deleteById(id);
    }
}
