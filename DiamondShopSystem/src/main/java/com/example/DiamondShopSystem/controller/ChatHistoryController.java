package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.OrderChatMessage;
import com.example.DiamondShopSystem.repository.OrderChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatHistoryController {

    @Autowired
    private OrderChatMessageRepository orderChatMessageRepository;

    @GetMapping("/history/{orderId}")
    public ResponseEntity<List<OrderChatMessage>> getChatHistory(@PathVariable Long orderId) {
        List<OrderChatMessage> chatHistory = orderChatMessageRepository.findByOrderOrderId(orderId);
        return ResponseEntity.ok(chatHistory);
    }
}

