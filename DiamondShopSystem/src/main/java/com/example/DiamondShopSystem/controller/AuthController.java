package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.dto.ReqRes;
import com.example.DiamondShopSystem.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register/staff")
    public ResponseEntity<ReqRes> registerStaff(@RequestBody ReqRes req, @RequestHeader ("Authorization") String token) {
        return ResponseEntity.ok(authService.registerStaff(req, token.substring(7)));
    }

    @PostMapping("/register/customer")
    public ResponseEntity<ReqRes> registerCustomer(@RequestBody ReqRes req) {
        return ResponseEntity.ok(authService.registerCustomer(req));
    }

    @PostMapping("/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req) {
        return ResponseEntity.ok(authService.refreshToken(req));
    }
}
