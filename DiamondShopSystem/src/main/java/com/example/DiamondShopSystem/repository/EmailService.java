package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.controller.EmailController;
import com.example.DiamondShopSystem.model.CustomOrder;
import com.example.DiamondShopSystem.model.Customer;
import com.example.DiamondShopSystem.model.EmailDetails;

public interface EmailService {

    // Method
    // To send a simple email
    String sendSimpleMail(EmailDetails details);

    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details);

    String sendHtmlEmail(EmailDetails details);

    void confirmOrder(Long orderId);

    void confirmCustomOrder(Long orderId);

    void confirmRequestCancelled(Long orderId);

    boolean sendResetPassword(String email);

    boolean sendContactUs(String name, String email, String message);

    void confirmPrepaid(Long orderId);
    void confirmUpdateStatus(Long orderId);
}

