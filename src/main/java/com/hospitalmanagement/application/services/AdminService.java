package com.hospitalmanagement.application.services;

import com.hospitalmanagement.application.dto.LoginDto;
import com.hospitalmanagement.application.dto.RegisterDto;
import com.hospitalmanagement.application.models.Admin;
import com.hospitalmanagement.application.models.Roles;
import com.hospitalmanagement.application.models.RoleName;
import com.hospitalmanagement.application.repositories.AdminRepository;
import com.hospitalmanagement.application.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService{
    private final AuthenticationManager authenticationManager ;
    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository ;
    private final PasswordEncoder passwordEncoder ;
    private final JwtUtilities jwtUtilities ;
    public Roles saveRole(Roles role) {
        return roleRepository.save(role);
    }
    public Admin saverUser(Admin user) {
        return adminRepository.save(user);
    }
    public ResponseEntity<?> register(RegisterDto registerDto) {
        if(adminRepository.existsByEmail(registerDto.getEmail())){
            return new ResponseEntity<>("email is already taken !", HttpStatus.SEE_OTHER);
        }else{
            Admin user = new Admin();
            user.setEmail(registerDto.getEmail());
            user.setFirstName(registerDto.getFirstName());
            user.setLastName(registerDto.getLastName());
            user.setPassword(passwordEncoder.encode(registerDto. getPassword()));
            String myrole = "user";
            if (registerDto.getUserRole().equals("") || registerDto. getUserRole().equals("user")) {
                myrole = "USER"; }
            if (registerDto.getUserRole().equals("admin")) { myrole = "ADMIN";
            }
            Roles role = roleRepository.findByRoleName(RoleName.valueOf (myrole));
            user.setUserRole(registerDto.getUserRole());
            user.setRoles(Collections.singletonList(role)); adminRepository.save(user);
            String token = jwtUtilities.generateToken(registerDto.getEmail( ), Collections.singletonList(role.getRoleName()));
            return new ResponseEntity<>(new BearerToken(token , "Bearer "),HttpStatus.OK);
        }
    }
    public ResponseEntity<?> authenticate(LoginDto loginDto) {
        Authentication authentication= authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(),loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
            Admin user = adminRepository.findByEmail(authentication.getName()). orElseThrow(() -> new UsernameNotFoundException("User not found"));
            List<String> rolesNames = new ArrayList<>();
            user.getRoles().forEach(r-> rolesNames.add(r.getRoleName()));
            String token = jwtUtilities.generateToken(user. getUsername(),rolesNames);
            return new ResponseEntity<>(new BearerToken(token,"Bearer"),HttpStatus.OK);

    }
    public Admin getDetails(String email){
        return adminRepository.findByEmail(email).get();
    }

}