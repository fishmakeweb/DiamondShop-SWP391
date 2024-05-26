package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.repository.CustomerRepository;
import com.example.DiamondShopSystem.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer findUserById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }

    public Customer saveUser(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateUser(Long id, Customer newCustomer) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setFullName(newCustomer.getFullName());
                    customer.setEmail(newCustomer.getEmail());
                    customer.setAddress(newCustomer.getAddress());
                    customer.setRegisteredDate(newCustomer.getRegisteredDate());
                    customer.setUsername(newCustomer.getUsername());
                    customer.setPassword(newCustomer.getPassword());
                    return customerRepository.save(customer);
                }).orElseGet(() -> {
                    newCustomer.setUserId(id);
                    return customerRepository.save(newCustomer);
                });
    }

    public void deleteUser(Long id) {
        customerRepository.deleteById(id);
    }
}
