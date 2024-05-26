package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.service.SupportService;
import com.example.DiamondShopSystem.model.Support;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/supports")
@CrossOrigin(origins = "http://localhost:3000")
public class SupportController {
    @Autowired
    private SupportService supportService;

    @GetMapping
    public List<Support> getAllSupports() {
        return supportService.findAllSupports();
    }

    @GetMapping("/{id}")
    public Support getSupportById(@PathVariable Long id) {
        return supportService.findSupportById(id);
    }

    @PostMapping
    public Support createSupport(@RequestBody Support support) {
        return supportService.saveSupport(support);
    }

    @PutMapping("/{id}")
    public Support updateSupport(@PathVariable Long id, @RequestBody Support support) {
        return supportService.updateSupport(id, support);
    }

    @DeleteMapping("/{id}")
    public void deleteSupport(@PathVariable Long id) {
        supportService.deleteSupport(id);
    }
}
