package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.dto.OrderDTO;
import com.example.DiamondShopSystem.dto.OrderTrackingDTO;
import com.example.DiamondShopSystem.dto.ReqRes;
import com.example.DiamondShopSystem.model.Order;
import com.example.DiamondShopSystem.model.OrderDetail;
import com.example.DiamondShopSystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.findAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.findOrderById(id);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/checkOut")
    public String saveOrder(@RequestHeader("Authorization") String token) {
        return orderService.checkOut(token.substring(7));
    }

    @PostMapping("/successCheckOut")
    public String successCheckOut(@RequestBody Map<String, Object> payload) {
        String payToken = (String) payload.get("payToken");
        return orderService.successCheckOut(payToken);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @GetMapping("/getcart")
    public OrderDTO getCart(@RequestHeader("Authorization") String token) {
        return orderService.getCart(token.substring(7));
    }

    @GetMapping("/getorder")
    public List<Order> getOrders(@RequestHeader("Authorization") String token) {
        return orderService.getOrders(token.substring(7));
    }

}
