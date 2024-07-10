package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.dto.CustomOrderDTO;
import com.example.DiamondShopSystem.dto.OrderChatDTO;
import com.example.DiamondShopSystem.dto.OrderDTO;
import com.example.DiamondShopSystem.model.OrderChatMessage;
import com.example.DiamondShopSystem.repository.CustomOrderRepository;
import com.example.DiamondShopSystem.repository.OrderChatMessageRepository;
import com.example.DiamondShopSystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class StaffChatHistoryController {

    @Autowired
    private OrderChatMessageRepository orderChatMessageRepository;

    @Autowired
    private CustomOrderRepository customOrderRepository;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/customorderlist")
    public List<CustomOrderDTO> getlistCustomOrder(){
        return customOrderRepository.findCustomOrdersWithChatMessages();
    }
    @GetMapping("/orderlist")
    public List<OrderChatDTO> getlistOrder(){
        return orderRepository.findOrdersWithChatMessages();
    }
}

