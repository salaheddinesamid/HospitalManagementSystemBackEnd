package com.hospitalmanagement.application.controller;


import com.hospitalmanagement.application.dto.AdminRegistrationDto;
import com.hospitalmanagement.application.dto.LoginDTO;
import com.hospitalmanagement.application.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @PostMapping("/new")
    public ResponseEntity<Object> newAdmin(@RequestBody AdminRegistrationDto adminRegistrationDto){
        return adminService.newAdmin(adminRegistrationDto);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody LoginDTO loginDTO){
        return adminService.authenticate(loginDTO);
    }
}
