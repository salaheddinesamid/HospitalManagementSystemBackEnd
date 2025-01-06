package com.hospitalmanagement.application.service;


import com.hospitalmanagement.application.dto.UserRegistrationDTO;
import com.hospitalmanagement.application.exception.UserAlreadyExistsException;
import com.hospitalmanagement.application.model.User;
import com.hospitalmanagement.application.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public ResponseEntity<Object> userRegistration(
            UserRegistrationDTO userRegistrationDTO
    ){
        if (userRepository.existsByEmail(userRegistrationDTO.getEmail())){
            User user = new User();
            user.setEmail(userRegistrationDTO.getEmail());
            user.setFirstName(userRegistrationDTO.getFirstName());
            user.setLastName(userRegistrationDTO.getLastName());
            user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
            return new ResponseEntity<>("User created", HttpStatus.OK);
        }else {
            throw new UserAlreadyExistsException();
        }

    }
}
