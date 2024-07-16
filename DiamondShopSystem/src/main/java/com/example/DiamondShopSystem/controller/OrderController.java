package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.dto.OrderDTO;
import com.example.DiamondShopSystem.model.Order;
import com.example.DiamondShopSystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/adminsale/orders")
    public List<Order> getAllOrders() {
        return orderService.findAllOrders();
    }

    @GetMapping("/adminsale/orders/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.findOrderById(id);
    }

    @PostMapping("/customer/create-order")
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/customer/checkOut")
    public String saveOrder(@RequestHeader("Authorization") String token) {
        return orderService.checkOut(token.substring(7));
    }

    @PostMapping("/customer/successCheckOut")
    public String successCheckOut(@RequestBody Map<String, Object> payload) {
        String payToken = (String) payload.get("payToken");
        return orderService.successCheckOut(payToken);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @GetMapping("/customer/getcart")
    public OrderDTO getCart(@RequestHeader("Authorization") String token) {
        return orderService.getCart(token.substring(7));
    }

    @GetMapping("/customer/getorder")
    public List<Order> getOrders(@RequestHeader("Authorization") String token) {
        return orderService.getOrders(token.substring(7));
    }

    @GetMapping("/filter-orderstatus/{orderStatusId}")
    public List<Order> getOrderStatusByOrderStatusId(@PathVariable Long orderStatusId) {
        return orderService.findAllByOrderStatusId(orderStatusId);
    }
}
