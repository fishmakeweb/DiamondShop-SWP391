package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.GIA;
import com.example.DiamondShopSystem.service.GIAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class GIAController {
    @Autowired
    private GIAService giaService;

    @GetMapping("/gias")
    public List<GIA> getAllGIAs() {
        return giaService.findAllGIAs();
    }

    @GetMapping("/gias/{id}")
    public GIA getGIAById(@PathVariable Long id) {
        return giaService.findGIAById(id);
    }

    @PostMapping("/secure/gias")
    public GIA createGIA(@RequestBody GIA gia) {
        return giaService.saveGIA(gia);
    }

    @PutMapping("/secure/gias/{id}")
    public GIA updateGIA(@PathVariable Long id, @RequestBody GIA gia) {
        return giaService.updateGIA(id, gia);
    }

    @DeleteMapping("/secure/gias/{id}")
    public void deleteGIA(@PathVariable Long id) {
        giaService.deleteGIA(id);
    }
}
