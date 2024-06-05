package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.Polish;
import com.example.DiamondShopSystem.service.PolishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class PolishController {
    @Autowired
    private PolishService polishService;

    @GetMapping("/polishes")
    public List<Polish> getAllPolishes() {
        return polishService.findAllPolishes();
    }

    @GetMapping("/polishes/{id}")
    public Polish getPolishById(@PathVariable Long id) {
        return polishService.findPolishById(id);
    }

    @PostMapping("/secure/polishes")
    public Polish createPolish(@RequestBody Polish polish) {
        return polishService.savePolish(polish);
    }

    @PutMapping("/secure/polishes/{id}")
    public Polish updatePolish(@PathVariable Long id, @RequestBody Polish polish) {
        return polishService.updatePolish(id, polish);
    }

    @DeleteMapping("/secure/polishes/{id}")
    public void deletePolish(@PathVariable Long id) {
        polishService.deletePolish(id);
    }
}
