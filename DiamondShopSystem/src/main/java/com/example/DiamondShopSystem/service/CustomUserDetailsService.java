package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.Customer;
import com.example.DiamondShopSystem.model.Staff;
import com.example.DiamondShopSystem.repository.CustomerRepository;
import com.example.DiamondShopSystem.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Try to find a staff first
        Staff staff = staffRepository.findByUsername(username).orElse(null);
        if (staff != null) {
            return staff;
        }

        // If no staff found, try to find a customer
        Customer customer = customerRepository.findByUsername(username).orElse(null);
        if (customer != null) {
            return customer;
        }

        // If no user found at all, throw an exception
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
