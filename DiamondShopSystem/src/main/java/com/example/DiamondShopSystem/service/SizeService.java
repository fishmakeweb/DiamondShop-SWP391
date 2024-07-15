package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.Size;
import com.example.DiamondShopSystem.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SizeService {
    @Autowired
    private SizeRepository sizeRepository;

    public List<Size> findAllSizes() {
        return sizeRepository.findAll();
    }

    public Size findSizeById(Long id) {
        Optional<Size> size = sizeRepository.findById(id);
        return size.orElse(null);
    }

    public Size saveSize(Size size) {
        return sizeRepository.save(size);
    }

    public Size updateSize(Long id, Size newSize) {
        return sizeRepository.findById(id)
                .map(existingSize -> {
                    existingSize.setType(newSize.getType());
                    existingSize.setSizeNumber(newSize.getSizeNumber());
                    existingSize.setUnit(newSize.getUnit());
                    return sizeRepository.save(existingSize);
                }).orElseGet(() -> {
                    newSize.setSizeId(id);
                    return sizeRepository.save(newSize);
                });
    }

    public void deleteSize(Long id) {
        sizeRepository.deleteById(id);
    }
}
