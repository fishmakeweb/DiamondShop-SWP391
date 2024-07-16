package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.Customer;
import com.example.DiamondShopSystem.service.CustomerService;
import com.example.DiamondShopSystem.service.JWTUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "*")
public class ForgotPasswordController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JWTUtils jwtUtils;

    static class EmailDTO {
        public String email;
    }

    // DTO for password reset data
    static class ResetPasswordDTO {
        public String token;
        public String password;
    }

    @PostMapping("/forgot_password")
    public ResponseEntity<?> processForgotPassword(@RequestBody EmailDTO emailDTO) {
        String email = emailDTO.email;
        String token = jwtUtils.generateToken(email);

        try {
            customerService.updateResetPasswordToken(token, email);
            String resetPasswordLink = "https://hephaestus.store/reset-password?token=" + token;  // Adjust your site URL here
            sendEmail(email, resetPasswordLink);
            return new ResponseEntity<>("Send successfully", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(
                    "Fail to send the email",
                    HttpStatus.BAD_REQUEST);
        }
    }

    private void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("admin@hepheathus.store", "Shopme Support");
        helper.setTo(recipientEmail);

        String subject = "Here's the link to reset your password";
        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }

    @PostMapping("/reset_password")
    public ResponseEntity<?> processResetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
        String token = resetPasswordDTO.token;
        String password = resetPasswordDTO.password;

        Customer customer = customerService.getByResetPasswordToken(token);
        if (customer == null) {
            return ResponseEntity.badRequest().body("Invalid Token");
        } else {
            if (jwtUtils.isTokenExpired(token)) {
                return ResponseEntity.badRequest().body("Token expired");
            } else {
                customerService.updatePassword(customer, password);
                return ResponseEntity.ok("You have successfully changed your password.");
            }

        }
    }
}
