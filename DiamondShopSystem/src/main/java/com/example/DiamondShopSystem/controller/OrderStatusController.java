package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.OrderStatus;
import com.example.DiamondShopSystem.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderStatusController {

    @Autowired
    private OrderStatusService orderStatusService;

    @GetMapping("/sale/orderstatus")
    public List<OrderStatus> getAllOrderStatuses() {
        return orderStatusService.findAllOrderStatuses();
    }

    @GetMapping("/sale/orderstatus/{id}")
    public OrderStatus getOrderStatusById(@PathVariable Long id) {
        return orderStatusService.findOrderStatusById(id);
    }

    @PostMapping("/sale/orderstatus")
    public OrderStatus createOrderStatus(@RequestBody OrderStatus orderStatus) {
        return orderStatusService.saveOrderStatus(orderStatus);
    }

    @PutMapping("/sale/orderstatus/{id}")
    public OrderStatus updateOrderStatus(@PathVariable Long id, @RequestBody OrderStatus orderStatus) {
        return orderStatusService.updateOrderStatus(id, orderStatus);
    }

    @DeleteMapping("/sale/orderstatus/{id}")
    public void deleteOrderStatus(@PathVariable Long id) {
        orderStatusService.deleteOrderStatus(id);
    }
}
