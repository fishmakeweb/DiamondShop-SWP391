package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.dto.CustomerDTO;
import com.example.DiamondShopSystem.dto.ReqRes;
import com.example.DiamondShopSystem.dto.RoleDTO;
import com.example.DiamondShopSystem.dto.StaffDTO;
import com.example.DiamondShopSystem.model.Customer;
import com.example.DiamondShopSystem.model.Staff;
import com.example.DiamondShopSystem.repository.CustomerRepository;
import com.example.DiamondShopSystem.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public ReqRes getProfile(String token) {
        String username = jwtUtils.extractUsername(token.substring(7)); // Remove "Bearer " from token
        Optional<Staff> staff = staffRepository.findByUsername(username);
        if (staff.isPresent()) {
            Staff user = staff.get();
            StaffDTO userDTO = convertToStaffDTO(user);
            return new ReqRes(200, null, "Profile fetched successfully", null, null, null, user.getFullName(), user.getEmail(), user.getUsername(), null, null, null, userDTO, null, null, null, null);
        }

        Optional<Customer> customer = customerRepository.findByUsername(username);
        if (customer.isPresent()) {
            Customer user = customer.get();
            CustomerDTO userDTO = convertToCustomerDTO(user);
            return new ReqRes(200, null, "Profile fetched successfully", null, null, null, user.getFullName(), user.getEmail(), user.getUsername(), null, user.getAddress(), user.getRegisteredDate(), null, userDTO, null, null, null);
        }

        throw new UsernameNotFoundException("User not found");
    }

    private StaffDTO convertToStaffDTO(Staff staff) {
        StaffDTO staffDTO = new StaffDTO();
        staffDTO.setStaffId(staff.getStaffId());
        staffDTO.setFullName(staff.getFullName());
        staffDTO.setEmail(staff.getEmail());
        staffDTO.setUsername(staff.getUsername());
        staffDTO.setPassword(staff.getPassword());

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(staff.getRole().getRoleId());
        roleDTO.setRoleName(staff.getRole().getRoleName());

        staffDTO.setRole(roleDTO);
        return staffDTO;
    }

    private CustomerDTO convertToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setUserId(customer.getUserId());
        customerDTO.setFullName(customer.getFullName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setUsername(customer.getUsername());
        customerDTO.setPassword(customer.getPassword());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setRegisteredDate(customer.getRegisteredDate());
        return customerDTO;
    }
}
