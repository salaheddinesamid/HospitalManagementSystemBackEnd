package com.hospitalmanagement.application.controllers;

import com.hospitalmanagement.application.dto.LoginDto;
import com.hospitalmanagement.application.dto.RegisterDto;
import com.hospitalmanagement.application.models.Admin;
import com.hospitalmanagement.application.repositories.AdminRepository;
import com.hospitalmanagement.application.services.AdminService;
import com.hospitalmanagement.application.services.BearerToken;
import com.hospitalmanagement.application.services.JwtUtilities;
import io.jsonwebtoken.Jwt;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin
public class AdminController {
    private final AdminService adminService;
    private final JwtUtilities jwtUtilities;
    private final AdminRepository adminRepository;
    @GetMapping("/test")
    public String test(){
        return "Hello";
    }
    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody RegisterDto registerDto) {
        return adminService.register(registerDto);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody LoginDto loginDto) {
        return adminService.authenticate(loginDto);
    }
    @GetMapping("/getadmin")
    public Admin getAdminDetails(@RequestHeader("Authorization") String token){
        String email = jwtUtilities.extractUserName(token.substring(7));
        return adminService.getDetails(email);
    }
}
