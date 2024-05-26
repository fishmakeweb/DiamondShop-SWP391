package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.OrderDetail;
import com.example.DiamondShopSystem.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order_details")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping
    public OrderDetail addOrderDetail(@RequestBody OrderDetail orderDetail) {
        return orderDetailService.saveOrderDetail(orderDetail);
    }

    @DeleteMapping
    public void removeOrderDetail(@RequestBody OrderDetail orderDetail) {
        orderDetailService.deleteOrderDetail(orderDetail);
    }
}
