package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.dto.CustomJewelryRequestDTO;
import com.example.DiamondShopSystem.dto.ListPriceCustomDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PriceCalculatorService {

    public int calculatePrice(CustomJewelryRequestDTO customJewelry) {
        ListPriceCustomDTO priceDTO = new ListPriceCustomDTO();
        float materialPrice = 0;
        float risePercent = 0;
        float manufacturingCost = calculateManufacturingCost(customJewelry);

        switch(customJewelry.getMaterial().getMaterialName()) {
            case "Vàng":
                materialPrice = priceDTO.getGoldPrice();
                risePercent = priceDTO.getGoldRisePercent();
                break;
            case "Bạc":
                materialPrice = priceDTO.getSilverPrice();
                risePercent = priceDTO.getSilverRisePercent();
                break;
            case "Bạch Kim":
                materialPrice = priceDTO.getPlatinumPrice();
                risePercent = priceDTO.getPlatinumRisePercent();
                break;
            case "Vàng Trắng":
                materialPrice = priceDTO.getWhiteGoldPrice();
                risePercent = priceDTO.getWhiteGoldRisePercent();
                break;
            case "Vàng Hồng":
                materialPrice = priceDTO.getRoseGoldPrice();
                risePercent = priceDTO.getRoseGoldRisePercent();
                break;
        }

        float sizePrice = 0;
        switch((int)customJewelry.getSize().getSizeNumber()) {
            case 6:
                sizePrice = priceDTO.getSize6Price();
                break;
            case 7:
                sizePrice = priceDTO.getSize7Price();
                break;
            case 8:
                sizePrice = priceDTO.getSize8Price();
                break;
            case 9:
                sizePrice = priceDTO.getSize9Price();
                break;
        }

        // Calculate the final price considering the rise percentage and manufacturing cost
        int finalPrice = Math.round((materialPrice + sizePrice + manufacturingCost) * (1 + risePercent));
        log.info("Calculated final price: {}", finalPrice);
        return finalPrice;
    }

    private float calculateManufacturingCost(CustomJewelryRequestDTO customJewelry) {
        ListPriceCustomDTO priceDTO = new ListPriceCustomDTO();
        float baseCost = priceDTO.getBaseManufacturingCost();
        float materialComplexityMultiplier = priceDTO.getMaterialComplexityMultiplier();

        float materialComplexity = 1.0f;

        switch(customJewelry.getMaterial().getMaterialName()) {
            case "Vàng":
                materialComplexity = 1.5f;
                break;
            case "Bạc":
                materialComplexity = 1.2f;
                break;
            case "Bạch Kim":
                materialComplexity = 1.8f;
                break;
            case "Vàng Trắng":
                materialComplexity = 1.6f;
                break;
            case "Vàng Hồng":
                materialComplexity = 1.7f;
                break;
        }

        // The manufacturing cost is calculated based on the base cost, material complexity, and a multiplier for complexity
        return baseCost * materialComplexity * materialComplexityMultiplier;
    }
}
