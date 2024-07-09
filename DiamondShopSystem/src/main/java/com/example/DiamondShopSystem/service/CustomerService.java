package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.Order;
import com.example.DiamondShopSystem.model.Provider;
import com.example.DiamondShopSystem.repository.CustomerRepository;
import com.example.DiamondShopSystem.model.Customer;
import com.example.DiamondShopSystem.repository.OrderRepository;
import com.example.DiamondShopSystem.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Autowired
    OrderRepository orderRepository;

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

    public Customer checkAndChangePassword(String token, String oldPassword, String newPassword) {
        String username = jwtUtils.extractUsername(token);
        Optional<Customer> customerOptional = customerRepository.findByUsername(username);

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();

            if (passwordEncoder.matches(oldPassword, customer.getPassword())) {
                customer.setPassword(passwordEncoder.encode(newPassword));
                customerRepository.save(customer);
                return customer;
            } else {
                throw new IllegalArgumentException("Old password does not match.");
            }
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
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

    public Customer processOAuthPostLogin(String username, String fullName) {
        Customer existingCustomer = customerRepository.getUserByUsername(username);

        if (existingCustomer != null) {
            return existingCustomer;
        }
        existingCustomer = customerRepository.findByAssignEmail(username);
        if (existingCustomer != null) {
            return existingCustomer;
        }
        Customer newCustomer = new Customer();
        newCustomer.setEmail(username);
        newCustomer.setUsername(username);
        newCustomer.setFullName(fullName);
        newCustomer.setRegisteredDate(new Date());
        newCustomer.setProvider(Provider.GOOGLE);
        Order order = new Order();
//                OrderStatus defaultOrderStatus = new OrderStatus();
        order.setUsername(newCustomer.getEmail());
//                defaultOrderStatus.setStatusId(1L);
        order.setOrderStatus(orderStatusRepository.findById(1L).get());
        orderRepository.save(order);
        customerRepository.save(newCustomer);
        return newCustomer;
    }
}
