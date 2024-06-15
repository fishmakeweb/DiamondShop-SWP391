package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.dto.DiamondAttributeDTO;
import com.example.DiamondShopSystem.model.Diamond;
import com.example.DiamondShopSystem.service.DiamondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class DiamondController {
    @Autowired
    private DiamondService diamondService;


    @GetMapping("/diamonds")
    public List<Diamond> getAllDiamonds() {
        return diamondService.findAllDiamonds();
    }

    @GetMapping("/diamonds/{id}")
    public Diamond getDiamondById(@PathVariable Long id) {
        return diamondService.findDiamondById(id);
    }

    @PostMapping("/secure/diamonds")
    public Diamond createDiamond(@RequestBody Diamond diamond) {
        return diamondService.saveDiamond(diamond);
    }

    @PutMapping("/secure/diamonds/{id}")
    public Diamond updateDiamond(@PathVariable Long id, @RequestBody Diamond diamond) {
        return diamondService.updateDiamond(id, diamond);
    }

    @DeleteMapping("/secure/diamonds/{id}")
    public void deleteDiamond(@PathVariable Long id) {
        diamondService.deleteDiamond(id);
    }

    @GetMapping("/diamonds/all")
    public DiamondAttributeDTO getAllDiamondAttributes() {
        return diamondService.getAllDiamondAttributes();
    }
}
