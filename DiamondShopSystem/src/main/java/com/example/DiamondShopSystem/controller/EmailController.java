package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.EmailDetails;
import com.example.DiamondShopSystem.repository.EmailService;
import com.example.DiamondShopSystem.repository.StaffRepository;
import com.example.DiamondShopSystem.service.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Annotation
@RestController
@RequestMapping("/api")
// Class
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private JWTUtils jwtUtils;


    @Autowired
    private StaffRepository staffRepository;


    static class EmailDTO {
        public String email;
    }

    static class ContactDTO{
        public String name;
        public String email;
        public String message;
    }

    // Sending a simple Email
    @PostMapping("/sale/sendMail")
    public String sendMail(@RequestBody EmailDetails details) {
        String status = emailService.sendSimpleMail(details);
        return status;
    }

    // Sending email with attachment
    @PostMapping("/sale/sendMailWithAttachment")
    public String sendMailWithAttachment(@RequestBody EmailDetails details) {
        String status = emailService.sendMailWithAttachment(details);
        return status;
    }

    @PostMapping("/sale/sendHtmlEmail")
    public String sendHtmlEmail(@RequestBody EmailDetails details) {
        String status;
        status = emailService.sendHtmlEmail(details);
        return status;
    }

    @PostMapping("/sale/confirmOrder")
    public void confirmOrder(@RequestParam Long orderId) {

        emailService.confirmOrder(orderId);

    }

    @PostMapping("/sale/confirmCustomOrder/{customOrderId}")
    public void confirmCustomOrder(@PathVariable Long customOrderId) {
        emailService.confirmCustomOrder(customOrderId);
    }

    @PostMapping("/sale/confirmCancelCustomOrder/{customOrderId}")
    public void confirmCancelCustomOrder(@PathVariable Long customOrderId) {

        emailService.confirmRequestCancelled(customOrderId);
    }

    @PostMapping("/public/forgot_password")
    public ResponseEntity<?> processForgotPassword(@RequestBody EmailDTO emailDTO) {
        try {
            boolean success = emailService.sendResetPassword(emailDTO.email);
            if (success) {
                return ResponseEntity.ok("Gửi thành công.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi gửi.");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PostMapping("/public/contact-us")
    public ResponseEntity<?> processContactUs(@RequestBody ContactDTO contactlDTO) {
        try {
            boolean success = emailService.sendContactUs(contactlDTO.name,contactlDTO.email, contactlDTO.message);
            if (success) {
                return ResponseEntity.ok("Gửi thành công.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi gửi.");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
