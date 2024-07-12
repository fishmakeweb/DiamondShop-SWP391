package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.dto.DiamondAttributeDTO;
import com.example.DiamondShopSystem.model.Diamond;
import com.example.DiamondShopSystem.repository.DiamondRepository;
import com.example.DiamondShopSystem.service.DiamondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DiamondController {
    @Autowired
    private DiamondService diamondService;
    @Autowired
    private DiamondRepository diamondRepository;

    @GetMapping("/public/diamonds")
    public List<Diamond> getAllDiamonds() {
        return diamondService.findAllDiamonds();
    }

    @GetMapping("/public/diamonds/{id}")
    public Diamond getDiamondById(@PathVariable Long id) {
        return diamondService.findDiamondById(id);
    }

    @PostMapping("/admin/diamonds")
    public Diamond createDiamond(@RequestBody Diamond diamond) {
        return diamondService.saveDiamond(diamond);
    }

    @PutMapping("/admin/diamonds/{id}")
    public Diamond updateDiamond(@PathVariable Long id, @RequestBody Diamond diamond) {
        return diamondService.updateDiamond(id, diamond);
    }

    @PutMapping("/admin/set/diamonds/{id}")
    public Diamond setSoldDiamond(@PathVariable Long id) {
        return diamondService.setSoldDiamond(id);
    }

    @PutMapping("/admin/status/diamonds/{id}")
    public void setSoldDiamond(@RequestHeader ("Authorization") String token, @PathVariable Long id, @RequestBody boolean status) {
        diamondService.setStatusDiamond(id, status);
    }

    @DeleteMapping("/admin/diamonds/{id}")
    public void deleteDiamond(@PathVariable Long id) {
        diamondService.deleteDiamond(id);
    }

    @GetMapping("/adminsale/diamonds/all")
    public DiamondAttributeDTO getAllDiamondAttributes() {
        return diamondService.getAllDiamondAttributes();
    }


    @GetMapping("/adminsale/diamonds/page")
    public Page<Diamond> getAllDiamondsPage(@RequestParam(defaultValue = "1") int page,
                                            @RequestParam(defaultValue = "8") int size) {
        Pageable pageable = PageRequest.of(page - 1, size); // page - 1 because page index starts from 0
        return diamondRepository.findAll(pageable);
    }
}
