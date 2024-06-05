package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.Clarity;
import com.example.DiamondShopSystem.service.ClarityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ClarityController {
    @Autowired
    private ClarityService clarityService;

    @GetMapping("/clarities")
    public List<Clarity> getAllClarities() {
        return clarityService.findAllClarities();
    }

    @GetMapping("/clarities/{id}")
    public Clarity getClarityById(@PathVariable Long id) {
        return clarityService.findClarityById(id);
    }

    @PostMapping("/secure/clarities")
    public Clarity createClarity(@RequestBody Clarity clarity) {
        return clarityService.saveClarity(clarity);
    }

    @PutMapping("/secure/clarities/{id}")
    public Clarity updateClarity(@PathVariable Long id, @RequestBody Clarity clarity) {
        return clarityService.updateClarity(id, clarity);
    }

    @DeleteMapping("/secure/clarities/{id}")
    public void deleteClarity(@PathVariable Long id) {
        clarityService.deleteClarity(id);
    }
}
