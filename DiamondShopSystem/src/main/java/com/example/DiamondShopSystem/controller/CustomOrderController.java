package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.dto.CustomOrderUpdateDTO;
import com.example.DiamondShopSystem.model.CustomJewelry;
import com.example.DiamondShopSystem.model.CustomOrder;
import com.example.DiamondShopSystem.model.Customer;
import com.example.DiamondShopSystem.model.OrderStatus;
import com.example.DiamondShopSystem.repository.CustomerRepository;
import com.example.DiamondShopSystem.service.CustomOrderService;
import com.example.DiamondShopSystem.service.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/custom-orders")
public class CustomOrderController {

    @Autowired
    private CustomOrderService customOrderService;

    @GetMapping
    public List<CustomOrder> getAllOrders(@RequestHeader ("Authorization") String token) {
        return customOrderService.getAllOrders(token.substring(7));
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



    @GetMapping("/getcustomorder")
    public List<CustomOrder> getCustomOrders(@RequestHeader("Authorization") String token) {
        return customOrderService.getCustomOrders(token.substring(7));
    }

    @PostMapping("/create-customorder")
    public ResponseEntity<?> createCustomOrder(@RequestBody CustomJewelry customJewelry, @RequestHeader("Authorization") String token) {
        customOrderService.createCustomOrder(customJewelry, token.substring(7));
        return ResponseEntity.ok().build(); // Adjust response based on your needs
    }

    @PutMapping("/updateAtr/{id}")
    public ResponseEntity<CustomOrder> updateCustomOrderFullPaid(@PathVariable Long id, @RequestBody CustomOrderUpdateDTO updateDTO, @RequestHeader("Authorization") String token) {
        CustomOrder updatedOrder = customOrderService.updateCustomOrderAtr(id, updateDTO, token.substring(7));
        return ResponseEntity.ok(updatedOrder);
    }

    @PutMapping("/verifyOrders/{id}")
    public ResponseEntity<CustomOrder> verifyOrders(@PathVariable Long id) {
        CustomOrder updatedOrder = customOrderService.verifyOrders(id);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomJewelry(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        String username = jwtUtils.extractUsername(token.substring(7));
        Optional<Customer> customer = customerRepository.findByUsername(username);
        if(customer!=null){
            customOrderService.deleteCustomOrder(id);
        }
    }

    @GetMapping("/checkOutCustomOrder/{customOrderId}")
    public String saveOrder(@RequestHeader("Authorization") String token,@PathVariable Long customOrderId ) {
        return customOrderService.checkOutCustomOrder(token.substring(7),customOrderId);
    }

    @PostMapping("/successCheckOutForCustomOrder")
    public String successCheckOut(@RequestBody Map<String, Object> payload) {
        String payToken = (String) payload.get("payToken");
        return customOrderService.successCheckOutForCustomOrder(payToken);
    }




}
