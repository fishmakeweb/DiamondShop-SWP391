package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.service.CaratService;
import com.example.DiamondShopSystem.model.Carat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/carats")
@CrossOrigin(origins = "http://localhost:3000")
public class CaratController {
    @Autowired
    private CaratService caratService;

    @GetMapping
    public List<Carat> getAllCarats() {
        return caratService.findAllCarats();
    }

    @GetMapping("/{id}")
    public Carat getCaratById(@PathVariable Long id) {
        return caratService.findCaratById(id);
    }

    @PostMapping
    public Carat createCarat(@RequestBody Carat carat) {
        return caratService.saveCarat(carat);
    }

    @PutMapping("/{id}")
    public Carat updateCarat(@PathVariable Long id, @RequestBody Carat carat) {
        return caratService.updateCarat(id, carat);
    }

    @DeleteMapping("/{id}")
    public void deleteCarat(@PathVariable Long id) {
        caratService.deleteCarat(id);
    }
}
