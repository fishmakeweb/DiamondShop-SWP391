package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.dto.AllDataDTO;
import com.example.DiamondShopSystem.dto.JewelryDTO;
import com.example.DiamondShopSystem.model.Jewelry;
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

    @GetMapping("/check-name/{name}")
    public boolean checkNameExists(@PathVariable String name) {
        return jewelryService.checkNameExists(name);
    }

//    @GetMapping("/jewelry/page")
//    public ResponseEntity<Page<JewelryDTO>> getJewelryPage(@RequestParam(defaultValue = "0") int page) {
//        Page<Jewelry> jewelryPage = jewelryService.getJewelryPage(page);
//        Page<JewelryDTO> dtoPage = jewelryPage.map(this::convertToDto);
//        return ResponseEntity.ok(dtoPage);
//    }

}
