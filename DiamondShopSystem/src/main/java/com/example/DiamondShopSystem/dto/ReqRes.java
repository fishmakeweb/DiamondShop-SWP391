package com.example.DiamondShopSystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqRes {
    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String fullName;
    private String email;
    private String username;
    private String password;
    private String address;
    private Date registeredDate;
    private StaffDTO staff;
    private CustomerDTO customer;
    private List<StaffDTO> staffs;
    private List<CustomerDTO> customers;
    private RoleDTO role;

    // Default constructor
    public ReqRes() {}

    // Constructor with all fields
    public ReqRes(int statusCode, String error, String message, String token, String refreshToken, String expirationTime, String fullName, String email, String username, String password, String address, Date registeredDate, StaffDTO staff, CustomerDTO customer, List<StaffDTO> staffs, List<CustomerDTO> customers, RoleDTO role) {
        this.statusCode = statusCode;
        this.error = error;
        this.message = message;
        this.token = token;
        this.refreshToken = refreshToken;
        this.expirationTime = expirationTime;
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.address = address;
        this.registeredDate = registeredDate;
        this.staff = staff;
        this.customer = customer;
        this.staffs = staffs;
        this.customers = customers;
        this.role = role;
    }

    public ReqRes(String fullName, String email, String address) {
        this.fullName = fullName;
        this.email = email;
        this.address = address;
    }

    public ReqRes(String fullName, String email, RoleDTO role) {
        this.fullName = fullName;
        this.email = email;
        this.role = role;
    }

    // Getters and setters
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public StaffDTO getStaff() {
        return staff;
    }

    public void setStaff(StaffDTO staff) {
        this.staff = staff;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public List<StaffDTO> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<StaffDTO> staffs) {
        this.staffs = staffs;
    }

    public List<CustomerDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerDTO> customers) {
        this.customers = customers;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }
}
