package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.Color;
import com.example.DiamondShopSystem.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColorService {
    @Autowired
    private ColorRepository colorRepository;

    public List<Color> findAllColors() {
        return colorRepository.findAll();
    }

    public Color findColorById(Long id) {
        Optional<Color> color = colorRepository.findById(id);
        return color.orElse(null);
    }

    public Color saveColor(Color color) {
        return colorRepository.save(color);
    }

    public Color updateColor(Long id, Color newColor) {
        return colorRepository.findById(id)
                .map(existingColor -> {
                    existingColor.setColorDescription(newColor.getColorDescription());
                    return colorRepository.save(existingColor);
                }).orElseGet(() -> {
                    newColor.setColorId(id);
                    return colorRepository.save(newColor);
                });
    }

    public void deleteColor(Long id) {
        colorRepository.deleteById(id);
    }
}
