package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.*;
import com.example.DiamondShopSystem.repository.*;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

// Annotation
@Service
// Class
// Implementing EmailService interface
public class EmailServiceImpl implements EmailService {

    @Autowired private JavaMailSender javaMailSender;

    @Autowired private OrderDetailRepository orderDetailRepository;

    @Autowired private OrderRepository orderRepository;

    @Autowired private OrderStatusRepository orderStatusRepository;

    @Autowired private CustomerRepository customerRepository;


    @Value("${spring.mail.username}")
    private String sender;
    // Method 1
    // To send a simple email
    public String sendSimpleMail(EmailDetails details)
    {

        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }

    // Method 2
    // To send an email with attachment
    public String
    sendMailWithAttachment(EmailDetails details)
    {
        // Creating a mime message
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            // Setting multipart as true for attachments to
            // be send
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(
                    details.getSubject());

            // Adding the attachment
            FileSystemResource file
                    = new FileSystemResource(
                    new File(details.getAttachment()));

            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);

            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }

    public String sendHtmlEmail(EmailDetails details) {
        // Creating a mime message
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            // Setting up the MIME message helper without multipart mode since no attachments are involved
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8"); // Set encoding if needed
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setSubject(details.getSubject());

            // Setting the HTML content of the mail
            mimeMessageHelper.setText(details.getMsgBody(), true); // The second parameter 'true' indicates HTML content

            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }
        // Catch block to handle MessagingException
        catch (MessagingException e) {
            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }
    public void confirmOrder(Long orderId) {
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        Order order = orderRepository.findById(orderId).get();
        order.setOrderStatus(orderStatusRepository.findById(4L).get());
        order.setOrderDate(new Date());
        orderRepository.save(order);
        Order newOrder = new Order();
        newOrder.setOrderStatus(orderStatusRepository.findById(1L).get());
        newOrder.setUsername(order.getUsername());
        orderRepository.save(newOrder);
        Customer customer = customerRepository.findByUsername(order.getUsername()).get();

        DecimalFormat df = new DecimalFormat("#.00");
        double totalAmount = order.getTotalPrice();

        String itemDetailsHtml = orderDetailList.stream()
                .map(item -> "<div style='display: flex; align-items: center; margin-bottom: 1.5vh;'>" +
                        "<div style='flex: 1; padding-right: 1rem;'>" +
                        "<img src='" + item.getProduct().getJewelry().getImg() + "' alt='" + item.getProduct().getJewelry().getName() + "' style='border-radius: 7px; width: 80%; height: 80%;' />" +
                        "</div>" +
                        "<div style='flex: 2; text-align: left;'>" +
                        "<div style='margin-bottom: 0.5rem;'>" +
                        "<p style='font-size: 0.9rem; font-weight: bold; margin: 0;'>" + item.getProduct().getJewelry().getName() + "</p>" +
                        "<p style='font-size: 0.9rem; color: grey; margin: 0;'>Quantity: " + item.getQuantity() + "</p>" +
                        "</div>" +
                        "<div style='text-align: right;'>" +
                        "<p style='font-size: 0.9rem; font-weight: bold; margin: 0;'>$" + df.format(item.getProduct().getJewelry().getPrice()) + "</p>" +
                        "</div>" +
                        "</div>" +
                        "</div>")
                .collect(Collectors.joining());

        String htmlContent = "<div style='background-color: #f2f3f8; padding: 20px; font-family: Arial, sans-serif; color: #455056; max-width: 670px; margin: 0 auto;'> ... " + itemDetailsHtml + " ... <div style='display: flex; justify-content: space-between; font-size: 1rem; font-weight: bold; margin-bottom: 2rem;'><span>Total:</span><span>$" + df.format(totalAmount) + "</span></div></div>";

        EmailDetails details = new EmailDetails();
        details.setRecipient(customer.getEmail());
        details.setSubject("Order Confirmation - " + orderId);
        details.setMsgBody(htmlContent);

        String emailStatus = sendHtmlEmail(details);
        System.out.println(emailStatus);
    }
}
