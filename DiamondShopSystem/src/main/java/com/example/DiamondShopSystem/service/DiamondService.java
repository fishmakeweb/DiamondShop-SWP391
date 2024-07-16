package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.dto.DiamondAttributeDTO;
import com.example.DiamondShopSystem.model.Diamond;
import com.example.DiamondShopSystem.model.Gia;
import com.example.DiamondShopSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DiamondService {
    @Autowired
    private DiamondRepository diamondRepository;

    @Autowired
    private GiaRepository giaRepository;


    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private CutRepository cutRepository;

    @Autowired
    private CaratRepository caratRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private CustomerRepository customerRepository;

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
            if (diamond.getGia() != null) {
                Gia gia = diamond.getGia();
                gia.setGiaNumber(generateUniqueGiaNumber());
                giaRepository.save(gia);
            }
            Diamond savedDiamond = diamondRepository.save(diamond);
            return savedDiamond;
        }

    private String generateUniqueGiaNumber() {
        String giaNumber;
        do {
            giaNumber = "GIA-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        } while (giaRepository.existsByGiaNumber(giaNumber));
        return giaNumber;
    }

    public Diamond updateDiamond(Long id, Diamond newDiamond) {
        return diamondRepository.findById(id).map(existingDiamond -> {
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

    public Diamond setSoldDiamond(Long id) {
            Diamond diamond = diamondRepository.findById(id).get();
            diamond.setSold(true);
            diamond = diamondRepository.save(diamond);
            return diamond;
    }

    public void setStatusDiamond(Long id, boolean status) {

            diamondRepository.findById(id).ifPresent(diamond -> {
                diamond.setSold(status);
                diamondRepository.save(diamond);
            });

    }

    public DiamondAttributeDTO getAllDiamondAttributes() {

            DiamondAttributeDTO dto = new DiamondAttributeDTO();
            dto.setMeasurements(measurementRepository.findAll());
            dto.setColors(colorRepository.findAll());
            dto.setCuts(cutRepository.findAll());
            dto.setCarats(caratRepository.findAll());
            dto.setClarities(clarityRepository.findAll());
            return dto;
        }
}
