package com.hospitalmanagement.application.controller;

import com.hospitalmanagement.application.dto.LoginDTO;
import com.hospitalmanagement.application.dto.UserRegistrationDTO;
import com.hospitalmanagement.application.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("new")
    public ResponseEntity<Object> newUser(@RequestBody UserRegistrationDTO userRegistrationDTO){
        return userService.userRegistration(userRegistrationDTO);
    }
    @PostMapping("authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody LoginDTO loginDTO){
        return userService.authentication(loginDTO);
    }
}
