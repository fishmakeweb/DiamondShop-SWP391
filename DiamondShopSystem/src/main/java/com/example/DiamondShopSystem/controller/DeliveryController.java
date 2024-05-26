package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.Delivery;
import com.example.DiamondShopSystem.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/deliveries")
@CrossOrigin(origins = "http://localhost:3000")
public class DeliveryController {
    @Autowired
    private DeliveryService deliveryService;

    @PostMapping
    public Delivery addDelivery(@RequestBody Delivery delivery) {
        return deliveryService.saveDelivery(delivery);
    }

    @DeleteMapping("/{id}")
    public void removeDelivery(@PathVariable Long id) {
        deliveryService.deleteDelivery(id);
    }
}
