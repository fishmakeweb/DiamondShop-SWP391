package com.example.DiamondShopSystem.controller;


import com.example.DiamondShopSystem.dto.PasswordChangeRequest;
import com.example.DiamondShopSystem.model.Customer;
import com.example.DiamondShopSystem.repository.CustomerRepository;
import com.example.DiamondShopSystem.service.CustomerService;
import com.example.DiamondShopSystem.service.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/admin/customers")
    public List<Customer> getAllCustomers() {
        return customerService.findAllCustomers();
    }

    @PutMapping("/customer/update-profile")
    public Customer updateUser(@RequestHeader ("Authorization") String token, @RequestBody Customer customer) {
        return customerService.updateUser(token.substring(7), customer);
    }

    @PutMapping("/customer/update-password")
    public Customer changePassword(@RequestHeader ("Authorization") String token,@RequestBody PasswordChangeRequest passwordChangeRequest) {
        return customerService.checkAndChangePassword( token.substring(7),passwordChangeRequest.getOldPassword(), passwordChangeRequest.getNewPassword());
    }


    static class ResetPasswordDTO {
        public String token;
        public String password;
    }
    @PostMapping("/public/reset_password")
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
    @GetMapping("/adminsale/customer-profile")
    public ResponseEntity<Optional<Customer>> getCustomerProfile(@RequestParam String username) {
        return ResponseEntity.ok(customerRepository.findByUsername(username));
    }
}
