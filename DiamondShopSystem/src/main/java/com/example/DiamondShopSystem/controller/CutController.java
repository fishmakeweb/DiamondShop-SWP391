package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.Cut;
import com.example.DiamondShopSystem.service.CutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class CutController {
    @Autowired
    private CutService cutService;

    @GetMapping("/cuts")
    public List<Cut> getAllCuts() {
        return cutService.findAllCuts();
    }

    @GetMapping("/cuts/{id}")
    public Cut getCutById(@PathVariable Long id) {
        return cutService.findCutById(id);
    }

    @PostMapping("/secure/cuts")
    public Cut createCut(@RequestBody Cut cut) {
        return cutService.saveCut(cut);
    }

    @PutMapping("/secure/cuts/{id}")
    public Cut updateCut(@PathVariable Long id, @RequestBody Cut cut) {
        return cutService.updateCut(id, cut);
    }

    @DeleteMapping("/secure/cuts/{id}")
    public void deleteCut(@PathVariable Long id) {
        cutService.deleteCut(id);
    }
}
