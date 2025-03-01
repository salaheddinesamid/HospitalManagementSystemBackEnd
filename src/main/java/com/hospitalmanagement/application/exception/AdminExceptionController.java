package com.hospitalmanagement.application.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdminExceptionController {

    @ExceptionHandler(value = AdminAlreadyExistsException.class)
    public ResponseEntity<Object> exception(){
        return new ResponseEntity<>("Admin already exists", HttpStatus.CONFLICT);

    }
}
