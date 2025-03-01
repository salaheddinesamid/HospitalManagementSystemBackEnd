package com.hospitalmanagement.application.service;


import com.hospitalmanagement.application.dto.AdminRegistrationDto;
import com.hospitalmanagement.application.dto.LoginDTO;
import com.hospitalmanagement.application.dto.TokenDTO;
import com.hospitalmanagement.application.exception.AdminAlreadyExistsException;
import com.hospitalmanagement.application.jwt.JwtUtil;
import com.hospitalmanagement.application.model.Admin;
import com.hospitalmanagement.application.model.Role;
import com.hospitalmanagement.application.model.RoleName;
import com.hospitalmanagement.application.repository.AdminRepository;
import com.hospitalmanagement.application.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.logging.Logger;

@Service
public class AdminService {
    private AdminRepository adminRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    private RoleRepository roleRepository;
    Logger logger = Logger.getLogger(AdminService.class.getName());

    @Autowired
    public AdminService(AdminRepository adminRepository,
                        PasswordEncoder passwordEncoder,
                        AuthenticationManager authenticationManager,
                        JwtUtil jwtUtil,
                        RoleRepository roleRepository
                        ){
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.roleRepository = roleRepository;
    }

    private String generateProfessionalId(String firstName, String lastName) {
        Random random = new Random();
        int randomNumber = 1000 + random.nextInt(9000); // Generates a 4-digit random number
        return firstName.charAt(0) + lastName + randomNumber;
    }

    public ResponseEntity<Object> newAdmin(AdminRegistrationDto adminRegistrationDto){
        if(!adminRepository.existsByEmail(adminRegistrationDto.getEmail())){
            Admin admin = new Admin();
            String token;
            String professionalNumber = generateProfessionalId(adminRegistrationDto.getFirstName(), adminRegistrationDto.getLastName());
            admin.setFirstName(adminRegistrationDto.getFirstName());
            admin.setLastName(adminRegistrationDto.getLastName());
            admin.setProfessionalNumber(professionalNumber);
            admin.setEmail(adminRegistrationDto.getEmail());
            String myrole = "ADMIN";

            Role role = roleRepository.findByRoleName(RoleName.valueOf(myrole));
            if (role == null) {
                return new ResponseEntity<>("Role not found!", HttpStatus.BAD_REQUEST);
            }
            admin.setRole(role);
            admin.setPassword(passwordEncoder.encode(adminRegistrationDto.getPassword()));
            adminRepository.save(admin);
            token = jwtUtil.generateToken(admin.getEmail(), admin.getRole().getRoleName().toString());

            return new ResponseEntity<>(new TokenDTO(token), HttpStatus.OK);
        }
        else{
            throw new AdminAlreadyExistsException();
        }
    }

    public ResponseEntity<Object> authenticate(LoginDTO loginDTO) {
        // Check if admin exists
        Admin admin = adminRepository.findByEmail(loginDTO.getEmail());
        if (admin == null) {
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
        }

        // Validate password using passwordEncoder.matches()
        if (!passwordEncoder.matches(loginDTO.getPassword(), admin.getPassword())) {
            return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
        }

        try {
            // Authenticate using AuthenticationManager
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token
            String token = jwtUtil.generateToken(admin.getEmail(), admin.getRole().getRoleName().toString());

            return ResponseEntity.ok(new TokenDTO(token));

        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Authentication failed", HttpStatus.UNAUTHORIZED);
        }
    }

}

