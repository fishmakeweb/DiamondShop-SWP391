package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.dto.CustomOrderUpdateDTO;
import com.example.DiamondShopSystem.model.CustomJewelry;
import com.example.DiamondShopSystem.model.CustomOrder;
import com.example.DiamondShopSystem.repository.CustomOrderRepository;
import com.example.DiamondShopSystem.repository.CustomerRepository;
import com.example.DiamondShopSystem.service.CustomOrderService;
import com.example.DiamondShopSystem.service.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomOrderController {

    @Autowired
    private CustomOrderService customOrderService;

    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomOrderRepository customOrderRepository;


    @GetMapping("/adminsale/custom-orders")
    public List<CustomOrder> getAllOrders() {
        return customOrderService.getAllOrders();
    }

    @GetMapping("/customer/custom-orders/{id}")
    public Optional<CustomOrder> getOrderById(@PathVariable Long id) {
        return customOrderService.getOrderById(id);
    }

    @PostMapping
    public CustomOrder createOrder(@RequestBody CustomOrder customOrder) {
        return customOrderService.createOrder(customOrder);
    }



    @GetMapping("/customer/get-customorder")
    public List<CustomOrder> getCustomOrders(@RequestHeader("Authorization") String token) {
        return customOrderService.getCustomOrders(token.substring(7));
    }

    @PostMapping("/customer/create-customorder")
    public ResponseEntity<?> createCustomOrder(@RequestBody CustomJewelry customJewelry, @RequestHeader("Authorization") String token) {
        customOrderService.createCustomOrder(customJewelry, token.substring(7));
        return ResponseEntity.ok().build(); // Adjust response based on your needs
    }

    @PutMapping("/sale/updateAtr/{id}")
    public ResponseEntity<CustomOrder> updateCustomOrderFullPaid(@PathVariable Long id, @RequestBody CustomOrderUpdateDTO updateDTO ) {
        CustomOrder updatedOrder = customOrderService.updateCustomOrderAtr(id, updateDTO);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/customer/delete-customorder/{id}")
    public void deleteCustomJewelry(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        String username = jwtUtils.extractUsername(token.substring(7));
        CustomOrder customOrder = customOrderRepository.findByUserNameAndCustomOrderId(username, id);
        if(customOrder!=null){
            customOrderService.deleteCustomOrder(id);
        }
    }

    @GetMapping("/customer/checkOutCustomOrder/{customOrderId}")
    public String saveOrder(@RequestHeader("Authorization") String token,@PathVariable Long customOrderId ) {
        return customOrderService.checkOutCustomOrder(token.substring(7),customOrderId);
    }

    @PostMapping("/customer/successCheckOutForCustomOrder")
    public String successCheckOut(@RequestBody Map<String, Object> payload) {
        String payToken = (String) payload.get("payToken");
        return customOrderService.successCheckOutForCustomOrder(payToken);
    }

    @GetMapping("/filter-custom-orderstatus/{orderStatusId}")
    public List<CustomOrder> getOrderStatusByOrderStatusId(@PathVariable Long orderStatusId) {
        return customOrderService.findAllByOrderStatusId(orderStatusId);
    }

    @PutMapping("/customer/request-cancel/{id}")
    public CustomOrder handleCancelCustomOrder(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        String username = jwtUtils.extractUsername(token.substring(7));
        CustomOrder customOrder = customOrderRepository.findByUserNameAndCustomOrderId(username, id);
        if (customOrder!=null) {
            return customOrderService.handleCancelCustomOrder(id);
        } else {
            throw new RuntimeException("Order not found with id " + id);
        }
    }
}
