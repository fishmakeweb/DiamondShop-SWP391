package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.Clarity;
import com.example.DiamondShopSystem.service.ClarityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClarityController {
    @Autowired
    private ClarityService clarityService;

    @GetMapping("/adminsale/clarities")
    public List<Clarity> getAllClarities() {
        return clarityService.findAllClarities();
    }

    @GetMapping("/adminsale/clarities/{id}")
    public Clarity getClarityById(@PathVariable Long id) {
        return clarityService.findClarityById(id);
    }

    @PostMapping("/admin/clarities")
    public Clarity createClarity(@RequestBody Clarity clarity) {
        return clarityService.saveClarity(clarity);
    }

    @PutMapping("/admin/clarities/{id}")
    public Clarity updateClarity(@PathVariable Long id, @RequestBody Clarity clarity) {
        return clarityService.updateClarity(id, clarity);
    }

    @DeleteMapping("/admin/clarities/{id}")
    public void deleteClarity(@PathVariable Long id) {
        clarityService.deleteClarity(id);
    }
}
