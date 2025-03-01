package com.hospitalmanagement.application.service;

import com.hospitalmanagement.application.model.Admin;
import com.hospitalmanagement.application.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminDetailsService implements UserDetailsService {
    private final AdminRepository adminRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin  = adminRepository.findByEmail(email);
        return admin;
    }
}
