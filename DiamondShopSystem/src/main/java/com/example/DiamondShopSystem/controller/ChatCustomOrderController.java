package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.dto.ChatMessage;
import com.example.DiamondShopSystem.dto.ChatMessageCustomOrderRequest;
import com.example.DiamondShopSystem.model.CustomOrderChatMessage;
import com.example.DiamondShopSystem.repository.CustomOrderChatMessageRepository;
import com.example.DiamondShopSystem.repository.CustomOrderRepository;
import com.example.DiamondShopSystem.service.CustomOrderService;
import com.example.DiamondShopSystem.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Controller
@Slf4j
public class ChatCustomOrderController {

    @Autowired
    private CustomOrderRepository customOrderRepository;

    @Autowired
    private CustomOrderChatMessageRepository customOrderChatMessageRepository;

    @Autowired
    private CustomOrderService customOrderService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private NotificationService notificationService;

    @MessageMapping("/custom-order-message")
    @SendTo("/topic/custom-order-messages")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;  // Broadcast the received message
    }



    @PostMapping("/custom-order-send")
    public ResponseEntity<?> sendChatMessage(@RequestBody ChatMessageCustomOrderRequest chatMessageRequest) {
        try {
            String username = chatMessageRequest.getUsername();
            CustomOrderChatMessage message = new CustomOrderChatMessage();
            message.setCustomOrder(customOrderRepository.findById(chatMessageRequest.getCustomOrderId()).orElseThrow());
            message.setUsername(username);
            message.setMessage(chatMessageRequest.getMessage()); // Changed from getMessage to getContent
            message.setTimestamp(new Date());
            customOrderService.saveChatMessage(message);
            log.info(message.getMessage());
            if (!username.contains("Staff")) notificationService.sendNotification("/topic/notifications", "New custom order chat message created by " + username);

            return ResponseEntity.ok().body("Message sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing request");
        }
    }

    @MessageMapping("/custom-order-chat/{customOrderId}")
    public void sendOrderMessage(@DestinationVariable Long customOrderId, @Payload ChatMessage chatMessage) {
        CustomOrderChatMessage message = new CustomOrderChatMessage();
        message.setCustomOrder(customOrderRepository.findById(customOrderId).orElseThrow(() -> new RuntimeException("Custom Order not found")));
        message.setUsername(chatMessage.getUsername());
        message.setMessage(chatMessage.getMessage()); // Consistent use of getContent
        message.setTimestamp(new Date());
        customOrderChatMessageRepository.save(message);
        chatMessage.setId(message.getId());
        log.info(message.getMessage());
        if (!message.getUsername().contains("Staff")) notificationService.sendNotification("/topic/notifications", "New custom order chat message created by " + message.getUsername()+" in custom order " + customOrderId);
        simpMessagingTemplate.convertAndSend("/topic/custom-orders/" + customOrderId, chatMessage);
    }


}

