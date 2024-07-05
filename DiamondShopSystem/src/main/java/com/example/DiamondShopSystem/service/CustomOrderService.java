package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.dto.CustomOrderUpdateDTO;
import com.example.DiamondShopSystem.model.CustomJewelry;
import com.example.DiamondShopSystem.model.CustomOrder;
import com.example.DiamondShopSystem.model.OrderStatus;
import com.example.DiamondShopSystem.repository.CustomJewelryRepository;
import com.example.DiamondShopSystem.repository.CustomOrderRepository;
import com.example.DiamondShopSystem.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomOrderService {

    @Autowired
    private CustomOrderRepository customOrderRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private CustomJewelryRepository customJewelryRepository;

    public List<CustomOrder> getAllOrders() {
        return customOrderRepository.findAll();
    }

    public Optional<CustomOrder> getOrderById(Long id) {
        return customOrderRepository.findById(id);
    }

    public CustomOrder createOrder(CustomOrder customOrder) {
        return customOrderRepository.save(customOrder);
    }

    public CustomOrder updateOrder(Long id, CustomOrder customOrderDetails) {
        Optional<CustomOrder> customOrderOptional = customOrderRepository.findById(id);
        if (customOrderOptional.isPresent()) {
            CustomOrder customOrder = customOrderOptional.get();
            customOrder.setUsername(customOrderDetails.getUsername());
            customOrder.setOrderStatus(customOrderDetails.getOrderStatus());
            customOrder.setCustomJewelry(customOrderDetails.getCustomJewelry());
            customOrder.setPrepaid(customOrderDetails.getPrepaid());
            customOrder.setFullpaid(customOrderDetails.getFullpaid());
            customOrder.setDescription(customOrderDetails.getDescription());
            customOrder.setStartDate(customOrderDetails.getStartDate());
            customOrder.setFinishDate(customOrderDetails.getFinishDate());
            return customOrderRepository.save(customOrder);
        } else {
            throw new RuntimeException("Order not found with id " + id);
        }
    }

    public CustomOrder updateCustomOrderAtr(Long id, CustomOrderUpdateDTO updateDTO){
        return customOrderRepository.findById(id)
                .map(customOrder -> {
                    customOrder.setFullpaid(updateDTO.getFullPaid());
                    customOrder.setDescription(updateDTO.getDescription());
                    customOrder.setFinishDate(updateDTO.getFinishDate());
                    return customOrderRepository.save(customOrder);
                })
                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
    }

    public CustomOrder verifyOrders(Long id){
        return customOrderRepository.findById(id)
                .map(customOrder -> {
                    customOrder.setOrderStatus(orderStatusRepository.findById(3L).get());
                    return customOrderRepository.save(customOrder);
                })
                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
    }

    public void deleteCustomOrder(Long id) {

        Optional<CustomOrder> customOrder = getOrderById(id);
        Long customJewelryId = customOrder.get().getCustomJewelry().getCustomJewelryId();
        customOrderRepository.deleteById(id);
        customJewelryRepository.deleteById(customJewelryId);
    }
    public void createCustomOrder(CustomJewelry customJewelry, String token) {
        // Save the CustomJewelry entity first
        CustomJewelry savedJewelry = customJewelryRepository.save(customJewelry);

        // Extract username from token
        String username = jwtUtils.extractUsername(token);

        // Create and save the CustomOrder entity with the saved CustomJewelry
        CustomOrder customOrder = new CustomOrder();
        customOrder.setUsername(username);
        customOrder.setCustomJewelry(savedJewelry);
        customOrder.setPrepaid(savedJewelry.getPrice() * 0.3f);
        customOrder.setFullpaid(savedJewelry.getPrice());
        customOrder.setOrderStatus(orderStatusRepository.findById(2L).orElseThrow(() -> new RuntimeException("Order status not found")));
        customOrder.setDescription("NOT PAID");
        customOrder.setStartDate(new Date());
        customOrder.setFinishDate(null);

        customOrderRepository.save(customOrder);
    }
    public List<CustomOrder> getCustomOrders(String token) {
        String username = jwtUtils.extractUsername(token);
        return customOrderRepository.findByUsername(username);
    }
}
