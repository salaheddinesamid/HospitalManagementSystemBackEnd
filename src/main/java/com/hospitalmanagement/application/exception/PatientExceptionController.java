package com.hospitalmanagement.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PatientExceptionController {

    @ExceptionHandler(value = PatientNotFoundException.class)
    public ResponseEntity<?> exception(){
        return new ResponseEntity<>("Patient does not exist", HttpStatus.NOT_FOUND);
    }
}
