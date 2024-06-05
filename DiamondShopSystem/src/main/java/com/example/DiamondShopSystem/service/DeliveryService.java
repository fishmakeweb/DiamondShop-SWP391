package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.Cut;
import com.example.DiamondShopSystem.model.Delivery;
import com.example.DiamondShopSystem.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {
    @Autowired
    private DeliveryRepository deliveryRepository;

    public List<Delivery> getAllDeliveries(){
        return deliveryRepository.findAll();
    }

    public Delivery getDeliveryById(Long id){
        Optional<Delivery> delivery = deliveryRepository.findById(id);
        return delivery.orElse(null);
    }

    public Delivery saveDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    public void deleteDelivery(Long id) {
        deliveryRepository.deleteById(id);
    }
}
