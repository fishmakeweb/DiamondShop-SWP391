package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.dto.ChatMessageRequest;
import com.example.DiamondShopSystem.dto.ChatMessage;
import com.example.DiamondShopSystem.model.OrderChatMessage;
import com.example.DiamondShopSystem.repository.OrderChatMessageRepository;
import com.example.DiamondShopSystem.repository.OrderRepository;
import com.example.DiamondShopSystem.service.OrderService;
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
public class ChatController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderChatMessageRepository orderChatMessageRepository;

    @Autowired
    private OrderService orderService;


    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;  // Broadcast the received message
    }

    @MessageMapping("/newUser")
    @SendTo("/topic/messages")
    public ChatMessage newUser(@Payload ChatMessage chatMessage) {
        chatMessage.setMessage( "joined the chat!");
        return chatMessage;  // Broadcast a user join event
    }


    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/send")
    public ResponseEntity<?> sendChatMessage(@RequestBody ChatMessageRequest chatMessageRequest) {
        try {
            String username = chatMessageRequest.getUsername();
            OrderChatMessage message = new OrderChatMessage();
            message.setOrder(orderRepository.findById(chatMessageRequest.getOrderId()).orElseThrow());
            message.setUsername(username);
            message.setMessage(chatMessageRequest.getMessage()); // Changed from getMessage to getContent
            message.setTimestamp(new Date());
            orderService.saveChatMessage(message);
            return ResponseEntity.ok().body("Message sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing request");
        }
    }

    @MessageMapping("/chat/{orderId}")
    public void sendOrderMessage(@DestinationVariable Long orderId, @Payload ChatMessage chatMessage) {
        OrderChatMessage message = new OrderChatMessage();
        message.setOrder(orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found")));
        message.setUsername(chatMessage.getUsername());
        message.setMessage(chatMessage.getMessage()); // Consistent use of getContent
        message.setTimestamp(new Date());
        orderChatMessageRepository.save(message);
        chatMessage.setId(message.getId());
        simpMessagingTemplate.convertAndSend("/topic/orders/" + orderId, chatMessage);
    }


}

