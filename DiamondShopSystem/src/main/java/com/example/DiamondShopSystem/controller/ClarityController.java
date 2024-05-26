package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.service.ClarityService;
import com.example.DiamondShopSystem.model.Clarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clarities")
@CrossOrigin(origins = "http://localhost:3000")
public class ClarityController {
    @Autowired
    private ClarityService clarityService;

    @GetMapping
    public List<Clarity> getAllClarities() {
        return clarityService.findAllClarities();
    }

    @GetMapping("/{id}")
    public Clarity getClarityById(@PathVariable Long id) {
        return clarityService.findClarityById(id);
    }

    @PostMapping
    public Clarity createClarity(@RequestBody Clarity clarity) {
        return clarityService.saveClarity(clarity);
    }

    @PutMapping("/{id}")
    public Clarity updateClarity(@PathVariable Long id, @RequestBody Clarity clarity) {
        return clarityService.updateClarity(id, clarity);
    }

    @DeleteMapping("/{id}")
    public void deleteClarity(@PathVariable Long id) {
        clarityService.deleteClarity(id);
    }
}
