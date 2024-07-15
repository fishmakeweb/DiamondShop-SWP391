package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.CustomJewelry;
import com.example.DiamondShopSystem.repository.CustomJewelryRepository;
import com.example.DiamondShopSystem.repository.CustomOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomJewelryService {

    private final CustomJewelryRepository customJewelryRepository;
    private final CustomOrderRepository customOrderRepository;


    @Autowired
    public CustomJewelryService(CustomJewelryRepository customJewelryRepository, CustomOrderRepository customOrderRepository) {
        this.customJewelryRepository = customJewelryRepository;
        this.customOrderRepository = customOrderRepository;
    }

    public List<CustomJewelry> getCustomjewelry(){
        return customJewelryRepository.findAll();
    }

    public CustomJewelry saveCustomJewelry(CustomJewelry customJewelry) {
        return customJewelryRepository.save(customJewelry);
    }


}
