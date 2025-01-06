package com.hospitalmanagement.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppointmentErrorController {

    @ExceptionHandler(value = AppointmentException.class)
    public ResponseEntity<Object> exception(AppointmentException e){
        return new ResponseEntity<>("An error occurred", HttpStatus.CONFLICT);
    }
}
