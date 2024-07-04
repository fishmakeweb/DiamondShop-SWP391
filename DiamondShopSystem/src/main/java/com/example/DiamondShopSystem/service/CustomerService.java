package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.repository.CustomerRepository;
import com.example.DiamondShopSystem.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JWTUtils jwtUtils;

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
                    return customerRepository.save(customer);
                }).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public Customer changeUserPassword(Long id, Customer newCustomer){
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setPassword(passwordEncoder.encode(newCustomer.getPassword()));
                    return customerRepository.save(customer);
                }).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public void deleteUser(Long id) {
        customerRepository.deleteById(id);
    }

    //FOR GOT PASSWORD
    public void updateResetPasswordToken(String token, String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByAssignEmail(email);
        if (customer != null) {
            customer.setResetPasswordToken(token);
            customerRepository.save(customer);
        } else {
            throw new UsernameNotFoundException("Could not find any customer with the email " + email);
        }
    }

    public Customer getByResetPasswordToken(String token) {
        return customerRepository.findByResetPasswordToken(token);
    }

    public void updatePassword(Customer customer, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        customer.setPassword(encodedPassword);

        customer.setResetPasswordToken(null);
        customerRepository.save(customer);
    }
}
