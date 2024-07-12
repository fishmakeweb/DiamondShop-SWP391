package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.dto.ReqRes;
import com.example.DiamondShopSystem.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/admin/register/staff")
    public ResponseEntity<ReqRes> registerStaff(@RequestBody ReqRes req) {
        return ResponseEntity.ok(authService.registerStaff(req));
    }

    @PostMapping("/auth/register/customer")
    public ResponseEntity<ReqRes> registerCustomer(@RequestBody ReqRes req) {
        return ResponseEntity.ok(authService.registerCustomer(req));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req) {
        return ResponseEntity.ok(authService.refreshToken(req));
    }
}
