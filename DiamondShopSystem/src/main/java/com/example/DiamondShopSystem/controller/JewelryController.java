package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.dto.AllDataDTO;
import com.example.DiamondShopSystem.dto.JewelryDTO;
import com.example.DiamondShopSystem.model.Jewelry;
import com.example.DiamondShopSystem.repository.JewelryRepository;
import com.example.DiamondShopSystem.repository.StaffRepository;
import com.example.DiamondShopSystem.service.JWTUtils;
import com.example.DiamondShopSystem.service.JewelryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JewelryController {
    @Autowired
    private JewelryService jewelryService;

    @Autowired
    private JewelryRepository jewelryRepository;

    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private StaffRepository staffRepository;

//    @GetMapping("/jewelry")
//    public List<Jewelry> getAllJewelry() {
//        return jewelryService.findAllJewelry();
//    }

    @GetMapping("/public/jewelry/{id}")
    public Jewelry getJewelryById(@PathVariable Long id) {
        return jewelryService.findJewelryById(id);
    }

    @PostMapping("/admin/jewelry")
    public Jewelry createJewelry(@RequestBody Jewelry jewelry) {
        return jewelryService.saveJewelry(jewelry);
    }

    @PutMapping("/admin/jewelry/{id}")
    public Jewelry updateJewelry(@PathVariable Long id, @RequestBody Jewelry jewelry) {
        return jewelryService.updateJewelry(id, jewelry);
    }

    @DeleteMapping("/admin/jewelry/{id}")
    public void deleteJewelry(@PathVariable Long id) {
        jewelryService.deleteJewelry(id);
    }


    @GetMapping("/adminsale/jewelry/all")
    public AllDataDTO getAllData() {
        return jewelryService.getAllData();
    }

    @GetMapping("/admin/jewelry/check-name/{name}")
    public boolean checkNameExists(@PathVariable String name) {
        return jewelryService.checkNameExists(name);
    }

    @GetMapping("/adminsale/jewelry/page")
    public Page<Jewelry> getAllJewelryPage(@RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "8") int size) {

        Pageable pageable = PageRequest.of(page - 1, size); // page - 1 because page index starts from 0
        return jewelryRepository.findAll(pageable);
    }

    @GetMapping("/public/jewelry")
    public Page<JewelryDTO> getJewelryByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<JewelryDTO> jewelryPage = jewelryRepository.findJewelryByPage(pageable);
        return jewelryPage;
    }



    @GetMapping("/public/new-release")
    public ResponseEntity<Page<JewelryDTO>> getNewReleaseByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<JewelryDTO> newReleasePage = jewelryRepository.findNewReleaseJewelry(pageable);
        if (newReleasePage.hasContent()) {
            return ResponseEntity.ok(newReleasePage);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/admin/status/jewelry/{id}")
    public void setSoldDiamond(@PathVariable Long id, @RequestBody boolean status) {
        jewelryService.setStatusJewelry(id, status);
    }
    @GetMapping("/adminsale/filter-category/{id}")
    public List<Jewelry> getJewelryByCategory(@PathVariable Long id) {
        return jewelryRepository.findJewelryByCategory(id);
    }
}
