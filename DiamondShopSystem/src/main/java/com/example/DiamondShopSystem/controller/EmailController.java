package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.EmailDetails;
import com.example.DiamondShopSystem.repository.EmailService;
import com.example.DiamondShopSystem.repository.StaffRepository;
import com.example.DiamondShopSystem.service.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Annotation
@RestController
@RequestMapping("/api/sale")
// Class
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private JWTUtils jwtUtils;


    @Autowired
    private StaffRepository staffRepository;


    // Sending a simple Email
    @PostMapping("/sendMail")
    public String sendMail(@RequestBody EmailDetails details) {
        String status = emailService.sendSimpleMail(details);
        return status;
    }

    // Sending email with attachment
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(@RequestBody EmailDetails details) {
        String status = emailService.sendMailWithAttachment(details);
        return status;
    }

    @PostMapping("/sendHtmlEmail")
    public String sendHtmlEmail(@RequestBody EmailDetails details) {
        String status;
        status = emailService.sendHtmlEmail(details);
        return status;
    }

    @PostMapping("/confirmOrder")
    public void confirmOrder(@RequestParam Long orderId) {

        emailService.confirmOrder(orderId);

    }

    @PostMapping("/confirmCustomOrder/{customOrderId}")
    public void confirmCustomOrder(@PathVariable Long customOrderId) {
        emailService.confirmCustomOrder(customOrderId);
    }

    @PostMapping("/confirmCancelCustomOrder/{customOrderId}")
    public void confirmCancelCustomOrder(@PathVariable Long customOrderId) {

        emailService.confirmRequestCancelled(customOrderId);
    }

}
