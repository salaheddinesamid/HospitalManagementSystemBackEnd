package com.hospitalmanagement.application.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionController {

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<Object> exception(UserAlreadyExistsException e){
        return new ResponseEntity<>("USER ALREADY EXISTS", HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<Object> badCredentials(BadCredentialsException e){
        return new ResponseEntity<>("BAD CREDENTIALS, PLEASE ENTER VALID VALUES",HttpStatus.NOT_FOUND);
    }
}
