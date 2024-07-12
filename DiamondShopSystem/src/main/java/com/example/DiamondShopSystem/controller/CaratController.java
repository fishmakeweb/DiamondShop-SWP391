package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.Carat;
import com.example.DiamondShopSystem.service.CaratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CaratController {
    @Autowired
    private CaratService caratService;

    @GetMapping("/adminsale/carats")
    public List<Carat> getAllCarats() {
        return caratService.findAllCarats();
    }

    @GetMapping("/adminsale/carats/{id}")
    public Carat getCaratById(@PathVariable Long id) {
        return caratService.findCaratById(id);
    }

    @PostMapping("/admin/carats")
    public Carat createCarat(@RequestBody Carat carat) {
        return caratService.saveCarat(carat);
    }

    @PutMapping("/admin/carats/{id}")
    public Carat updateCarat(@PathVariable Long id, @RequestBody Carat carat) {
        return caratService.updateCarat(id, carat);
    }

    @DeleteMapping("/admin/carats/{id}")
    public void deleteCarat(@PathVariable Long id) {
        caratService.deleteCarat(id);
    }
}
