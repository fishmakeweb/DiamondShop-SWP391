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
@CrossOrigin(origins = "http://localhost:3000")
public class DiamondController {
    @Autowired
    private DiamondService diamondService;


    @GetMapping("/diamonds")
    public List<Diamond> getAllDiamonds() {
        return diamondService.findAllDiamonds();
    }

    @GetMapping("/diamonds/{id}")
    public Diamond getDiamondById(@PathVariable Long id) {
        return diamondService.findDiamondById(id);
    }

    @PostMapping("/secure/diamonds")
    public Diamond createDiamond(@RequestBody Diamond diamond,@RequestHeader ("Authorization") String token) {
        return diamondService.saveDiamond(diamond,token.substring(7));
    }

    @PutMapping("/secure/diamonds/{id}")
    public Diamond updateDiamond(@PathVariable Long id, @RequestBody Diamond diamond) {
        return diamondService.updateDiamond(id, diamond);
    }

    @PutMapping("/secure/set/diamonds/{id}")
    public void setSoldDiamond(@PathVariable Long id) {
        diamondService.setSoldDiamond(id);
    }

    @DeleteMapping("/secure/diamonds/{id}")
    public void deleteDiamond(@PathVariable Long id) {
        diamondService.deleteDiamond(id);
    }

    @GetMapping("/diamonds/all")
    public DiamondAttributeDTO getAllDiamondAttributes() {
        return diamondService.getAllDiamondAttributes();
    }

    @Autowired
    private DiamondRepository diamondRepository;
    @GetMapping("/diamonds/page")
    public Page<Diamond> getAllDiamondsPage(@RequestParam(defaultValue = "1") int page,
                                            @RequestParam(defaultValue = "8") int size) {
        Pageable pageable = PageRequest.of(page - 1, size); // page - 1 because page index starts from 0
        return diamondRepository.findAll(pageable);
    }
}
