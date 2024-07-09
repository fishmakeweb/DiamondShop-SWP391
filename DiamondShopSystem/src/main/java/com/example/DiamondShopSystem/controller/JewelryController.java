package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.dto.AllDataDTO;
import com.example.DiamondShopSystem.dto.JewelryDTO;
import com.example.DiamondShopSystem.model.Jewelry;
import com.example.DiamondShopSystem.model.Staff;
import com.example.DiamondShopSystem.repository.JewelryRepository;
import com.example.DiamondShopSystem.repository.StaffRepository;
import com.example.DiamondShopSystem.service.JWTUtils;
import com.example.DiamondShopSystem.service.JewelryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class JewelryController {
    @Autowired
    private JewelryService jewelryService;

    @Autowired
    private JewelryRepository jewelryRepository;

    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private StaffRepository staffRepository;

    @GetMapping("/jewelry")
    public List<Jewelry> getAllJewelry() {
        return jewelryService.findAllJewelry();
    }

    @GetMapping("/jewelry/{id}")
    public Jewelry getJewelryById(@PathVariable Long id) {
        return jewelryService.findJewelryById(id);
    }

    @PostMapping("/secure/jewelry")
    public Jewelry createJewelry(@RequestBody Jewelry jewelry, @RequestHeader ("Authorization") String token) {
        return jewelryService.saveJewelry(jewelry, token.substring(7));
    }

    @PutMapping("/secure/jewelry/{id}")
    public Jewelry updateJewelry(@PathVariable Long id, @RequestBody Jewelry jewelry, @RequestHeader ("Authorization") String token) {
        return jewelryService.updateJewelry(id, jewelry, token.substring(7));
    }

    @DeleteMapping("/secure/jewelry/{id}")
    public void deleteJewelry(@PathVariable Long id, @RequestHeader ("Authorization") String token) {
        jewelryService.deleteJewelry(id, token.substring(7));
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
    public AllDataDTO getAllData(@RequestHeader ("Authorization") String token) {
        return jewelryService.getAllData(token.substring(7));
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
