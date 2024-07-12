package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.Size;
import com.example.DiamondShopSystem.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SizeController {
    @Autowired
    private SizeService sizeService;

    @GetMapping("/customer/sizes")
    public List<Size> getAllSizes() {
        return sizeService.findAllSizes();
    }

    @GetMapping("/adminsale/sizes/{id}")
    public Size getSizeById(@PathVariable Long id) {
        return sizeService.findSizeById(id);
    }

    @PostMapping("/admin/sizes")
    public Size createSize(@RequestBody Size size) {
        return sizeService.saveSize(size);
    }

    @PutMapping("/admin/sizes/{id}")
    public Size updateSize(@PathVariable Long id, @RequestBody Size size) {
        return sizeService.updateSize(id, size);
    }

    @DeleteMapping("/admin/sizes/{id}")
    public void deleteSize(@PathVariable Long id) {
        sizeService.deleteSize(id);
    }
}
