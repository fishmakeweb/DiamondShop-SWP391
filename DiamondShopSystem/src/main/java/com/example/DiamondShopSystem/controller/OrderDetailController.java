package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.dto.AddProductRequest;
import com.example.DiamondShopSystem.model.Jewelry;
import com.example.DiamondShopSystem.model.OrderDetail;
import com.example.DiamondShopSystem.model.Product;
import com.example.DiamondShopSystem.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order_details")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderDetailController {
    //
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

    @PostMapping("/addToCart")
    public void addProductToOrder(@RequestHeader("Authorization") String token, @RequestParam Long productId) {
        orderDetailService.addToCart(token.substring(7), productId);
    }

    @GetMapping
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailService.getAllOrderDetails();
    }

    @PostMapping("/updateQuantity")
    public ResponseEntity<OrderDetail> updateOrderDetailQuantity(@RequestParam Long orderDetailId, @RequestParam int quantity) {
        try {
            OrderDetail updatedOrderDetail = orderDetailService.updateQuantity(orderDetailId, quantity);
            return ResponseEntity.ok(updatedOrderDetail);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
