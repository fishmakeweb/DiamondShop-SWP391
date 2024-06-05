package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.Delivery;
import com.example.DiamondShopSystem.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/secure/deliveries")
@CrossOrigin(origins = "http://localhost:3000")
public class DeliveryController {
    @Autowired
    private DeliveryService deliveryService;

    @GetMapping
    public List<Delivery> getAllDeliveries(){
        return deliveryService.getAllDeliveries();
    }

    @GetMapping("{id}")
    public Delivery getDeliveryById(@PathVariable Long id){
        return deliveryService.getDeliveryById(id);
    }

    @PostMapping
    public Delivery addDelivery(@RequestBody Delivery delivery) {
        return deliveryService.saveDelivery(delivery);
    }

    @DeleteMapping("/{id}")
    public void removeDelivery(@PathVariable Long id) {
        deliveryService.deleteDelivery(id);
    }
}
