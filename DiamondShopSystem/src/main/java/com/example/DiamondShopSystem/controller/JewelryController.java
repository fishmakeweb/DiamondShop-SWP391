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

    @GetMapping("/public/jewelry/{id}")
    public Jewelry getJewelryById(@PathVariable Long id) {
        return jewelryService.findJewelryById(id);
    }

    @PostMapping("/admin/jewelry")
    public Jewelry createJewelry(@RequestBody Jewelry jewelry, @RequestHeader ("Authorization") String token) {
        return jewelryService.saveJewelry(jewelry, token.substring(7));
    }

    @PutMapping("/admin/jewelry/{id}")
    public Jewelry updateJewelry(@PathVariable Long id, @RequestBody Jewelry jewelry, @RequestHeader ("Authorization") String token) {
        return jewelryService.updateJewelry(id, jewelry, token.substring(7));
    }

    @DeleteMapping("/admin/jewelry/{id}")
    public void deleteJewelry(@PathVariable Long id, @RequestHeader ("Authorization") String token) {
        jewelryService.deleteJewelry(id, token.substring(7));
    }


    @GetMapping("/adminsale/jewelry/all")
    public AllDataDTO getAllData() {
        return jewelryService.getAllData();
    }

    @GetMapping("/public/attributes/all")
    public AllDataDTO getAllAttributes() {
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

//    @GetMapping("/public/jewelry")
//    public Page<JewelryDTO> getJewelryByPage(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "12") int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<JewelryDTO> jewelryPage = jewelryRepository.findJewelryByPage(pageable);
//        return jewelryPage;
//    }

    @GetMapping("/public/jewelry")
    public Page<JewelryDTO> getAvailableJewelryByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(required = false) List<Long> materialIds,
            @RequestParam(required = false) List<Long> categoryIds,
            @RequestParam(required = false) List<Long> shapeIds,
            @RequestParam(required = false) List<Long> sizeIds,
            @RequestParam(required = false) Float minPrice,
            @RequestParam(required = false) Float maxPrice) {

        Pageable pageable = PageRequest.of(page, size);
        return jewelryRepository.findByFilters(materialIds, categoryIds, shapeIds, sizeIds, minPrice, maxPrice, pageable);
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

}
