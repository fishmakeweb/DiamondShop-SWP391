package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.dto.CustomJewelryRequestDTO;
import com.example.DiamondShopSystem.model.Color;
import com.example.DiamondShopSystem.model.CustomJewelry;
import com.example.DiamondShopSystem.model.Customer;
import com.example.DiamondShopSystem.repository.CustomerRepository;
import com.example.DiamondShopSystem.service.CustomJewelryService;
import com.example.DiamondShopSystem.service.CustomOrderService;
import com.example.DiamondShopSystem.service.JWTUtils;
import com.example.DiamondShopSystem.service.PriceCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomJewelryController {

    private final CustomJewelryService customJewelryService;
    @Autowired
    public CustomJewelryController(CustomJewelryService customJewelryService) {
        this.customJewelryService = customJewelryService;
    }
    @Autowired
    private PriceCalculatorService priceCalculatorService;

    @GetMapping("/public/customJewelry")
    public List<CustomJewelry> getAllCustomJewelry() {
        return customJewelryService.getCustomjewelry();
    }



    @PostMapping("/public/calculate-price")
    public CustomJewelry calculatePrice(@RequestBody CustomJewelryRequestDTO customJewelryRequest) {
        float price = priceCalculatorService.calculatePrice(customJewelryRequest);

        CustomJewelry customJewelry = new CustomJewelry();
        customJewelry.setCustomJewelryId(customJewelryRequest.getCustomJewelryId());
        if(customJewelryRequest.getDiamond()!=null){
            customJewelry.setDiamond(customJewelryRequest.getDiamond());
        }
        customJewelry.setMaterial(customJewelryRequest.getMaterial());
        customJewelry.setCategory(customJewelryRequest.getCategory());
        customJewelry.setSize(customJewelryRequest.getSize());
        customJewelry.setShape(customJewelryRequest.getShape());
        customJewelry.setNote(customJewelryRequest.getNote());
        customJewelry.setPrice(price);

        return customJewelry;
    }

}
