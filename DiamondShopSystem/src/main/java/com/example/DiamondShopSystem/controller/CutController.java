package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.Cut;
import com.example.DiamondShopSystem.service.CutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CutController {
    @Autowired
    private CutService cutService;

    @GetMapping("/adminsale/cuts")
    public List<Cut> getAllCuts() {
        return cutService.findAllCuts();
    }

    @GetMapping("/adminsale/cuts/{id}")
    public Cut getCutById(@PathVariable Long id) {
        return cutService.findCutById(id);
    }

    @PostMapping("/admin/cuts")
    public Cut createCut(@RequestBody Cut cut) {
        return cutService.saveCut(cut);
    }

    @PutMapping("/admin/cuts/{id}")
    public Cut updateCut(@PathVariable Long id, @RequestBody Cut cut) {
        return cutService.updateCut(id, cut);
    }

    @DeleteMapping("/admin/cuts/{id}")
    public void deleteCut(@PathVariable Long id) {
        cutService.deleteCut(id);
    }
}
