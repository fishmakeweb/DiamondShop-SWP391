package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.dto.DiamondAttributeDTO;
import com.example.DiamondShopSystem.model.Diamond;
import com.example.DiamondShopSystem.model.Gia;
import com.example.DiamondShopSystem.model.Jewelry;
import com.example.DiamondShopSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DiamondService {
    @Autowired
    private DiamondRepository diamondRepository;

    @Autowired
    private GiaRepository giaRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ShapeRepository shapeRepository;

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private CutRepository cutRepository;

    @Autowired
    private CaratRepository caratRepository;

    @Autowired
    private ClarityRepository clarityRepository;
    public List<Diamond> findAllDiamonds() {
        return diamondRepository.findAll();
    }

    public Diamond findDiamondById(Long id) {
        Optional<Diamond> diamond = diamondRepository.findById(id);
        return diamond.orElse(null);
    }


    @Transactional
    public Diamond saveDiamond(Diamond diamond) {
        Diamond savedDiamond = diamondRepository.save(diamond);
        productService.createProductForDiamond(savedDiamond);
        return savedDiamond;
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


    public DiamondAttributeDTO getAllDiamondAttributes() {
        DiamondAttributeDTO dto = new DiamondAttributeDTO();
        dto.setShapes(shapeRepository.findAll());
        dto.setMeasurements(measurementRepository.findAll());
        dto.setColors(colorRepository.findAll());
        dto.setCuts(cutRepository.findAll());
        dto.setCarats(caratRepository.findAll());
        dto.setClarities(clarityRepository.findAll());
        return dto;
    }
}
