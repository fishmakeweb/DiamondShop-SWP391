package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.dto.AllDataDTO;
import com.example.DiamondShopSystem.dto.JewelryDTO;
import com.example.DiamondShopSystem.model.Jewelry;
import com.example.DiamondShopSystem.repository.JewelryRepository;
import com.example.DiamondShopSystem.service.JewelryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class JewelryController {
    @Autowired
    private JewelryService jewelryService;

    @Autowired
    private JewelryRepository jewelryRepository;
    @GetMapping("/jewelry")
    public List<Jewelry> getAllJewelry() {
        return jewelryService.findAllJewelry();
    }

    @GetMapping("/jewelry/{id}")
    public Jewelry getJewelryById(@PathVariable Long id) {
        return jewelryService.findJewelryById(id);
    }

    @PostMapping("/secure/jewelry")
    public Jewelry createJewelry(@RequestBody Jewelry jewelry) {
        return jewelryService.saveJewelry(jewelry);
    }

    @PutMapping("/secure/jewelry/{id}")
    public Jewelry updateJewelry(@PathVariable Long id, @RequestBody Jewelry jewelry) {
        return jewelryService.updateJewelry(id, jewelry);
    }

    @DeleteMapping("/secure/jewelry/{id}")
    public void deleteJewelry(@PathVariable Long id) {
        jewelryService.deleteJewelry(id);
    }

    @GetMapping("/jewelry/categories/{categoryId}")
    public List<Jewelry> getJewelryByCategory(@PathVariable Long categoryId) {
        return jewelryService.findAllByCategoryId(categoryId);
    }

    @GetMapping("/jewelry/price/range")
    public List<Jewelry> getJewelryByPriceRange(@RequestParam("min") float minPrice, @RequestParam("max") float maxPrice) {
        return jewelryService.findAllByPriceRange(minPrice, maxPrice);
    }

    @GetMapping("/jewelry/all")
    public AllDataDTO getAllData() {
        return jewelryService.getAllData();
    }

    @GetMapping("/jewelry/check-name/{name}")
    public boolean checkNameExists(@PathVariable String name) {
        return jewelryService.checkNameExists(name);
    }

    @GetMapping("/jewelry/page")
    public Page<Jewelry> getAllJewelryPage(@RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "8") int size) {
        Pageable pageable = PageRequest.of(page - 1, size); // page - 1 because page index starts from 0
        return jewelryRepository.findAll(pageable);
    }

}
