package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.Gia;
import com.example.DiamondShopSystem.service.GiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class GiaController {
    @Autowired
    private GiaService giaService;

    @GetMapping("/gias")
    public List<Gia> getAllGIAs() {
        return giaService.findAllGias();
    }

    @GetMapping("/gias/{id}")
    public Gia getGIAById(@PathVariable Long id) {
        return giaService.findGiaById(id);
    }

    @PostMapping("/secure/gias")
    public Gia createGIA(@RequestBody Gia gia) {
        return giaService.saveGia(gia);
    }

    @PutMapping("/secure/gias/{id}")
    public Gia updateGIA(@PathVariable Long id, @RequestBody Gia gia) {
        return giaService.updateGia(id, gia);
    }

    @DeleteMapping("/secure/gias/{id}")
    public void deleteGIA(@PathVariable Long id) {
        giaService.deleteGIA(id);
    }
}
