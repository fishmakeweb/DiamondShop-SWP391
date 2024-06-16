package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.OrderProcess;
import com.example.DiamondShopSystem.service.OrderProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderProcessController {

    @Autowired
    private OrderProcessService orderProcessService;

    @GetMapping("/orderprocess")
    public List<OrderProcess> getAllOrderProcesses() {
        return orderProcessService.findAllOrderProcesses();
    }

    @GetMapping("/orderprocess/{id}")
    public OrderProcess getOrderProcessById(@PathVariable Long id) {
        return orderProcessService.findOrderProcessById(id);
    }

    @PostMapping("/secure/orderprocess")
    public OrderProcess createOrderProcess(@RequestBody OrderProcess orderProcess) {
        return orderProcessService.saveOrderProcess(orderProcess);
    }

    @PutMapping("/secure/orderprocess/{id}")
    public OrderProcess updateOrderProcess(@PathVariable Long id, @RequestBody OrderProcess orderProcess) {
        return orderProcessService.updateOrderProcess(id, orderProcess);
    }

    @DeleteMapping("/secure/orderprocess/{id}")
    public void deleteOrderProcess(@PathVariable Long id) {
        orderProcessService.deleteOrderProcess(id);
    }
}
