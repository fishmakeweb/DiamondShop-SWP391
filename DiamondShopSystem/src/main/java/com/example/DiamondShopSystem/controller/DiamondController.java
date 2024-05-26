package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.service.DiamondService;
import com.example.DiamondShopSystem.model.Diamond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/diamonds")
@CrossOrigin(origins = "http://localhost:3000")
public class DiamondController {
    @Autowired
    private DiamondService diamondService;

    @GetMapping
    public List<Diamond> getAllDiamonds() {
        return diamondService.findAllDiamonds();
    }

    @GetMapping("/{id}")
    public Diamond getDiamondById(@PathVariable Long id) {
        return diamondService.findDiamondById(id);
    }

    @PostMapping
    public Diamond createDiamond(@RequestBody Diamond diamond) {
        return diamondService.saveDiamond(diamond);
    }

    @PutMapping("/{id}")
    public Diamond updateDiamond(@PathVariable Long id, @RequestBody Diamond diamond) {
        return diamondService.updateDiamond(id, diamond);
    }

    @DeleteMapping("/{id}")
    public void deleteDiamond(@PathVariable Long id) {
        diamondService.deleteDiamond(id);
    }
}
