package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.Size;
import com.example.DiamondShopSystem.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class SizeController {
    @Autowired
    private SizeService sizeService;

    @GetMapping("/sizes")
    public List<Size> getAllSizes() {
        return sizeService.findAllSizes();
    }

    @GetMapping("/sizes/{id}")
    public Size getSizeById(@PathVariable Long id) {
        return sizeService.findSizeById(id);
    }

    @PostMapping("/secure/sizes")
    public Size createSize(@RequestBody Size size) {
        return sizeService.saveSize(size);
    }

    @PutMapping("/secure/sizes/{id}")
    public Size updateSize(@PathVariable Long id, @RequestBody Size size) {
        return sizeService.updateSize(id, size);
    }

    @DeleteMapping("/secure/sizes/{id}")
    public void deleteSize(@PathVariable Long id) {
        sizeService.deleteSize(id);
    }
}
