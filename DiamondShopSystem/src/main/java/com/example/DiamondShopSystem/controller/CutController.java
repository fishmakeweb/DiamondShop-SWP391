package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.service.CutService;
import com.example.DiamondShopSystem.model.Cut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cuts")
@CrossOrigin(origins = "http://localhost:3000")
public class CutController {
    @Autowired
    private CutService cutService;

    @GetMapping
    public List<Cut> getAllCuts() {
        return cutService.findAllCuts();
    }

    @GetMapping("/{id}")
    public Cut getCutById(@PathVariable Long id) {
        return cutService.findCutById(id);
    }

    @PostMapping
    public Cut createCut(@RequestBody Cut cut) {
        return cutService.saveCut(cut);
    }

    @PutMapping("/{id}")
    public Cut updateCut(@PathVariable Long id, @RequestBody Cut cut) {
        return cutService.updateCut(id, cut);
    }

    @DeleteMapping("/{id}")
    public void deleteCut(@PathVariable Long id) {
        cutService.deleteCut(id);
    }
}
