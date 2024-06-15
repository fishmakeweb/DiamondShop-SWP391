package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.Gia;
import com.example.DiamondShopSystem.repository.GiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GiaService {
    @Autowired
    private GiaRepository giaRepository;

    public List<Gia> findAllGias() {
        return giaRepository.findAll();
    }

    public Gia findGiaById(Long id) {
        Optional<Gia> gia = giaRepository.findById(id);
        return gia.orElse(null);
    }

    public Gia saveGia(Gia gia) {
        return giaRepository.save(gia);
    }

    public Gia updateGia(Long id, Gia newGia) {
        return giaRepository.findById(id)
                .map(existingGia -> {
                    existingGia.setIssueDate(newGia.getIssueDate());
                    return giaRepository.save(existingGia);
                }).orElseGet(() -> {
                    newGia.setGiaId(id);
                    return giaRepository.save(newGia);
                });
    }

    public void deleteGIA(Long id) {
        giaRepository.deleteById(id);
    }
}
