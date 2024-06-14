
package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.dto.CustomerDTO;
import com.example.DiamondShopSystem.dto.ReqRes;
import com.example.DiamondShopSystem.dto.RoleDTO;
import com.example.DiamondShopSystem.dto.StaffDTO;
import com.example.DiamondShopSystem.model.Cart;
import com.example.DiamondShopSystem.model.Customer;
import com.example.DiamondShopSystem.model.Role;
import com.example.DiamondShopSystem.model.Staff;
import com.example.DiamondShopSystem.repository.CartRepository;
import com.example.DiamondShopSystem.repository.CustomerRepository;
import com.example.DiamondShopSystem.repository.RoleRepository;
import com.example.DiamondShopSystem.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AuthService {

    private static final Logger LOGGER = Logger.getLogger(AuthService.class.getName());

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CartRepository cartRepository;

    public ReqRes registerStaff(ReqRes registrationRequest) {
        ReqRes resp = new ReqRes();

        if (isUsernameExists(registrationRequest.getUsername())) {
            resp.setStatusCode(400);
            resp.setError("Username already exists");
            return resp;
        }
        try {
            Role role = roleRepository.findByRoleName(registrationRequest.getRole().getRoleName());
            if (role == null) {
                resp.setStatusCode(400);
                resp.setError("No role name : " + registrationRequest.getRole().getRoleName());
                return resp;
            }


            Staff staff = new Staff();
            staff.setEmail(registrationRequest.getEmail());
            staff.setUsername(registrationRequest.getUsername());
            staff.setRole(role);
            staff.setFullName(registrationRequest.getFullName());
            staff.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

            Staff staffResult = staffRepository.save(staff);
            if (staffResult.getStaffId() > 0) {
                StaffDTO staffDTO = convertToStaffDTO(staffResult);
                resp.setStaff(staffDTO);
                resp.setMessage("User Saved Successfully");
                resp.setStatusCode(200);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error registering staff", e);
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }

        return resp;
    }

    public ReqRes registerCustomer(ReqRes registrationRequest) {
        ReqRes resp = new ReqRes();

        if (isUsernameExists(registrationRequest.getUsername())) {
            resp.setStatusCode(400);
            resp.setError("Username already exists");
            return resp;
        }

        try {
            Customer customer = new Customer();
            customer.setEmail(registrationRequest.getEmail());
            customer.setUsername(registrationRequest.getUsername());
            customer.setFullName(registrationRequest.getFullName());
            customer.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            customer.setAddress(registrationRequest.getAddress());
            customer.setRegisteredDate(Date.valueOf(LocalDate.now())); // Set to current date

            Customer customerResult = customerRepository.save(customer);
            Cart cart = new Cart();
            cart.setCustomer(customerResult);
            Cart cartResult = cartRepository.save(cart);
            if (customerResult.getUserId() > 0) {
                CustomerDTO customerDTO = convertToCustomerDTO(customerResult, cartResult);
                resp.setCustomer(customerDTO);
                resp.setMessage("Customer Saved Successfully");
                resp.setStatusCode(200);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error registering customer", e);
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }

        return resp;
    }

    private boolean isUsernameExists(String username) {
        return staffRepository.findByUsername(username).isPresent() || customerRepository.findByUsername(username).isPresent();
    }

    public ReqRes login(ReqRes loginRequest) {
        ReqRes resp = new ReqRes();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            Optional<Staff> staff = staffRepository.findByUsername(loginRequest.getUsername());
            if (staff.isPresent()) {
                Staff user = staff.get();
                String jwt = jwtUtils.generateToken(user);
                String refreshToken = jwtUtils.generateRefreshToken(user);

                StaffDTO userDTO = convertToStaffDTO(user);

                resp.setStatusCode(200);
                resp.setToken(jwt);
                resp.setRefreshToken(refreshToken);
                resp.setExpirationTime("24Hrs");
                resp.setMessage("Login Successfully");
                resp.setStaff(userDTO);
                return resp;
            }

            Optional<Customer> customer = customerRepository.findByUsername(loginRequest.getUsername());
            if (customer.isPresent()) {
                Customer user = customer.get();
                String jwt = jwtUtils.generateToken(user);
                String refreshToken = jwtUtils.generateRefreshToken(user);

                Optional<Cart> cart = cartRepository.findByCustomer(user);
                CustomerDTO userDTO = convertToCustomerDTO(user, cart.orElse(null));

                resp.setStatusCode(200);
                resp.setToken(jwt);
                resp.setRefreshToken(refreshToken);
                resp.setExpirationTime("24Hrs");
                resp.setMessage("Login Successfully");
                resp.setCustomer(userDTO);
                return resp;
            }

            throw new UsernameNotFoundException("User not found");

        } catch (UsernameNotFoundException e) {
            LOGGER.log(Level.WARNING, "User not found", e);
            resp.setStatusCode(404);
            resp.setMessage(e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error logging in", e);
            resp.setStatusCode(500);
            resp.setMessage(e.getMessage());
        }

        return resp;
    }

    public ReqRes refreshToken(ReqRes refreshTokenRequest) {
        ReqRes response = new ReqRes();

        try {
            String username = jwtUtils.extractUsername(refreshTokenRequest.getRefreshToken());
            Optional<Staff> staff = staffRepository.findByUsername(username);
            if (staff.isPresent() && jwtUtils.isTokenValid(refreshTokenRequest.getRefreshToken(), staff.get())) {
                String jwt = jwtUtils.generateToken(staff.get());
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshTokenRequest.getRefreshToken());
                response.setExpirationTime("24Hr");
                response.setMessage("Successfully Refreshed Token");
                return response;
            }

            Optional<Customer> customer = customerRepository.findByUsername(username);
            if (customer.isPresent() && jwtUtils.isTokenValid(refreshTokenRequest.getRefreshToken(), customer.get())) {
                String jwt = jwtUtils.generateToken(customer.get());
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshTokenRequest.getRefreshToken());
                response.setExpirationTime("24Hr");
                response.setMessage("Successfully Refreshed Token");
                return response;
            }

            response.setStatusCode(401);
            response.setMessage("Invalid Refresh Token");

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error refreshing token", e);
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }

        return response;
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

    private CustomerDTO convertToCustomerDTO(Customer customer, Cart cart) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setUserId(customer.getUserId());
        customerDTO.setFullName(customer.getFullName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setUsername(customer.getUsername());
        customerDTO.setPassword(customer.getPassword());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setRegisteredDate(customer.getRegisteredDate());
        customerDTO.setCart(cart);
        return customerDTO;
    }
}
