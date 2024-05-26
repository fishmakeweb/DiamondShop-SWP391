package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.service.SizeService;
import com.example.DiamondShopSystem.model.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sizes")
@CrossOrigin(origins = "http://localhost:3000")
public class SizeController {
    @Autowired
    private SizeService sizeService;

    @GetMapping
    public List<Size> getAllSizes() {
        return sizeService.findAllSizes();
    }

    @GetMapping("/{id}")
    public Size getSizeById(@PathVariable Long id) {
        return sizeService.findSizeById(id);
    }

    @PostMapping
    public Size createSize(@RequestBody Size size) {
        return sizeService.saveSize(size);
    }

    @PutMapping("/{id}")
    public Size updateSize(@PathVariable Long id, @RequestBody Size size) {
        return sizeService.updateSize(id, size);
    }

    @DeleteMapping("/{id}")
    public void deleteSize(@PathVariable Long id) {
        sizeService.deleteSize(id);
    }
}
