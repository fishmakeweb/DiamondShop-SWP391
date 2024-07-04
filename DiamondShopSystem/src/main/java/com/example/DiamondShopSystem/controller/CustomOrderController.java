package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.CustomJewelry;
import com.example.DiamondShopSystem.model.CustomOrder;
import com.example.DiamondShopSystem.model.Customer;
import com.example.DiamondShopSystem.repository.CustomerRepository;
import com.example.DiamondShopSystem.service.CustomOrderService;
import com.example.DiamondShopSystem.service.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/custom-orders")
public class CustomOrderController {

    @Autowired
    private CustomOrderService customOrderService;

    @GetMapping
    public List<CustomOrder> getAllOrders() {
        return customOrderService.getAllOrders();
    }
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private CustomerRepository customerRepository;



    @GetMapping("/{id}")
    public Optional<CustomOrder> getOrderById(@PathVariable Long id) {
        return customOrderService.getOrderById(id);
    }

    @PostMapping
    public CustomOrder createOrder(@RequestBody CustomOrder customOrder) {
        return customOrderService.createOrder(customOrder);
    }

    @PutMapping("/{id}")
    public CustomOrder updateOrder(@PathVariable Long id, @RequestBody CustomOrder customOrderDetails) {
        return customOrderService.updateOrder(id, customOrderDetails);
    }


    @GetMapping("/getcustomorder")
    public List<CustomOrder> getCustomOrders(@RequestHeader("Authorization") String token) {
        return customOrderService.getCustomOrders(token.substring(7));
    }

    @PostMapping("/create-customorder")
    public ResponseEntity<?> createCustomOrder(@RequestBody CustomJewelry customJewelry, @RequestHeader("Authorization") String token) {
        customOrderService.createCustomOrder(customJewelry, token.substring(7));
        return ResponseEntity.ok().build(); // Adjust response based on your needs
    }

    @DeleteMapping("/{id}")
    public void deleteCustomJewelry(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        String username = jwtUtils.extractUsername(token.substring(7));
        Optional<Customer> customer = customerRepository.findByUsername(username);
        if(customer!=null){
            customOrderService.deleteCustomOrder(id);
        }
    }
}
