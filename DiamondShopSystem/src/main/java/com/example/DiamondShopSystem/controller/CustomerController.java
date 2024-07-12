package com.example.DiamondShopSystem.controller;


import com.example.DiamondShopSystem.dto.PasswordChangeRequest;
import com.example.DiamondShopSystem.model.Customer;
import com.example.DiamondShopSystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

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

}
