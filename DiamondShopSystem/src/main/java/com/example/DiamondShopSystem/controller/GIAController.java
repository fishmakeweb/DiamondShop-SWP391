package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.service.GIAService;
import com.example.DiamondShopSystem.model.GIA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/gias")
@CrossOrigin(origins = "http://localhost:3000")
public class GIAController {
    @Autowired
    private GIAService giaService;

    @GetMapping
    public List<GIA> getAllGIAs() {
        return giaService.findAllGIAs();
    }

    @GetMapping("/{id}")
    public GIA getGIAById(@PathVariable Long id) {
        return giaService.findGIAById(id);
    }

    @PostMapping
    public GIA createGIA(@RequestBody GIA gia) {
        return giaService.saveGIA(gia);
    }

    @PutMapping("/{id}")
    public GIA updateGIA(@PathVariable Long id, @RequestBody GIA gia) {
        return giaService.updateGIA(id, gia);
    }

    @DeleteMapping("/{id}")
    public void deleteGIA(@PathVariable Long id) {
        giaService.deleteGIA(id);
    }
}
