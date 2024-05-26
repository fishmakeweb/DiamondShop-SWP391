package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.service.PolishService;
import com.example.DiamondShopSystem.model.Polish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/polishes")
@CrossOrigin(origins = "http://localhost:3000")
public class PolishController {
    @Autowired
    private PolishService polishService;

    @GetMapping
    public List<Polish> getAllPolishes() {
        return polishService.findAllPolishes();
    }

    @GetMapping("/{id}")
    public Polish getPolishById(@PathVariable Long id) {
        return polishService.findPolishById(id);
    }

    @PostMapping
    public Polish createPolish(@RequestBody Polish polish) {
        return polishService.savePolish(polish);
    }

    @PutMapping("/{id}")
    public Polish updatePolish(@PathVariable Long id, @RequestBody Polish polish) {
        return polishService.updatePolish(id, polish);
    }

    @DeleteMapping("/{id}")
    public void deletePolish(@PathVariable Long id) {
        polishService.deletePolish(id);
    }
}
