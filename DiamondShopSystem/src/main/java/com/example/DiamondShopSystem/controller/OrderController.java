package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.dto.ReqRes;
import com.example.DiamondShopSystem.model.Order;
import com.example.DiamondShopSystem.model.OrderDetail;
import com.example.DiamondShopSystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping ("/checkOut/{id}")
    public Order saveOrder(@RequestBody Order order) { return  orderService.saveOrderOnCheckOut(order); }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

//    @GetMapping("/getCart/{userId}")
//    public List<OrderDetail> getCartByUserId(@PathVariable Long userId) {
//        return orderService.getCartByUserId(userId);
//    }
@GetMapping("/getcart")
public List<OrderDetail> getCart(@RequestHeader("Authorization") String token) {
    return orderService.getCart(token.substring(7));
}
}
