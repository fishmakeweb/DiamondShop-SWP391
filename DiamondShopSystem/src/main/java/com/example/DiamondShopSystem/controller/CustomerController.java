package com.example.DiamondShopSystem.controller;


import com.example.DiamondShopSystem.model.Customer;
import com.example.DiamondShopSystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/secure/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.findAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getUserById(@PathVariable Long id) {
        return customerService.findUserById(id);
    }

    @PostMapping
    public Customer createUser(@RequestBody Customer customer) {
        return customerService.saveUser(customer);
    }

    @PutMapping("/{id}")
    public Customer updateUser(@PathVariable Long id, @RequestBody Customer customer) {
        return customerService.updateUser(id, customer);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        customerService.deleteUser(id);
    }
}
