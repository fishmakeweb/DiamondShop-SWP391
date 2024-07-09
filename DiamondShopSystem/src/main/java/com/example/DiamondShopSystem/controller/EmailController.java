package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.EmailDetails;
import com.example.DiamondShopSystem.model.Staff;
import com.example.DiamondShopSystem.repository.EmailService;
import com.example.DiamondShopSystem.repository.StaffRepository;
import com.example.DiamondShopSystem.service.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// Annotation
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
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
    public void confirmOrder(@RequestParam Long orderId, @RequestHeader("Authorization") String token) {
        String username = jwtUtils.extractUsername(token.substring(7));
        Optional<Staff> staff = staffRepository.findByUsername(username);
        if (staff.isPresent()) {
            emailService.confirmOrder(orderId);
        }
    }

    @PostMapping("/confirmCustomOrder/{customOrderId}")
    public void confirmCustomOrder(@PathVariable Long customOrderId, @RequestHeader("Authorization") String token) {
        String username = jwtUtils.extractUsername(token.substring(7));
        Optional<Staff> staff = staffRepository.findByUsername(username);
        if (staff.isPresent()) {
            emailService.confirmCustomOrder(customOrderId);
        }
    }

    @PostMapping("/confirmCancelCustomOrder/{customOrderId}")
    public void confirmCancelCustomOrder(@PathVariable Long customOrderId, @RequestHeader("Authorization") String token) {
        String username = jwtUtils.extractUsername(token.substring(7));
        Optional<Staff> staff = staffRepository.findByUsername(username);
        if (staff.isPresent()) {
            emailService.confirmRequestCancelled(customOrderId);
        }
    }
}
