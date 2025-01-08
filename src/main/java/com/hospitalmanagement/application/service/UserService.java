package com.hospitalmanagement.application.service;


import com.hospitalmanagement.application.dto.LoginDTO;
import com.hospitalmanagement.application.dto.TokenDTO;
import com.hospitalmanagement.application.dto.UserRegistrationDTO;
import com.hospitalmanagement.application.exception.BadCredentialsException;
import com.hospitalmanagement.application.exception.UserAlreadyExistsException;
import com.hospitalmanagement.application.jwt.JwtUtil;
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
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }


    // User registration
    public ResponseEntity<Object> userRegistration(
            UserRegistrationDTO userRegistrationDTO
    ){
        if (!userRepository.existsByEmail(userRegistrationDTO.getEmail())){
            User user = new User();
            user.setEmail(userRegistrationDTO.getEmail());
            user.setFirstName(userRegistrationDTO.getFirstName());
            user.setLastName(userRegistrationDTO.getLastName());
            user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
            user.setRole("USER");
            userRepository.save(user);
            String token = jwtUtil.generateToken(userRegistrationDTO.getEmail(),"USER");
            return new ResponseEntity<>(new TokenDTO(token), HttpStatus.OK);
        }else {
            throw new UserAlreadyExistsException();
        }

    }

    // User authentication;
    public ResponseEntity<Object> authentication(LoginDTO loginDTO){
       boolean userExists = userRepository.existsByEmail(loginDTO.getEmail());
       String token = "";
       if (userExists){
            User user = userRepository.findByEmail(loginDTO.getEmail());
            if (user.getPassword().equals(passwordEncoder.encode(loginDTO.getPassword()))){
               token = jwtUtil.generateToken(loginDTO.getEmail(),
                        "USER");

            }
           return new ResponseEntity<>(new TokenDTO(token),HttpStatus.OK);
       }else {
           throw new BadCredentialsException();
       }
    }

    public ResponseEntity<Object> resetPassword(
            String email,
            String newPassword
    ){
        User user = userRepository.findByEmail(email);
        user.setPassword(passwordEncoder.encode(newPassword));
        return new ResponseEntity<>("Password reset successfully",HttpStatus.OK);
    }

    /*

    public void deleteUserData(
            Integer userId
    ){
        User user = userRepository.findById(userId).get();

    }
    public ResponseEntity<Object> deleteAccount(
            String email
    ){
        User user = userRepository.findByEmail(email);

    }*/
}
