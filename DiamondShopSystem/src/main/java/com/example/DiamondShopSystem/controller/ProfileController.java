package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.dto.ReqRes;
import com.example.DiamondShopSystem.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "http://localhost:3000")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    public ResponseEntity<ReqRes> getProfile(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(profileService.getProfile(token));
    }
}
