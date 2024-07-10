package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.CustomOrderChatMessage;
import com.example.DiamondShopSystem.model.OrderChatMessage;
import com.example.DiamondShopSystem.repository.CustomOrderChatMessageRepository;
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
public class ChatCustomOrderHistoryController {

    @Autowired
    private CustomOrderChatMessageRepository customOrderChatMessageRepository;

    @GetMapping("/custom-order-history/{customOrderId}")
    public ResponseEntity<List<CustomOrderChatMessage>> getChatHistory(@PathVariable Long customOrderId) {
        List<CustomOrderChatMessage> chatHistory = customOrderChatMessageRepository.findByCustomOrderCustomOrderId(customOrderId);
        return ResponseEntity.ok(chatHistory);
    }
}

