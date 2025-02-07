package com.hospitalmanagement.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NationalIdExceptionController {

    @ExceptionHandler(value = NationalIdNotValidException.class)
    public ResponseEntity<Object> nationalIdNotValid(){
        return new ResponseEntity<>("National Id is not valid", HttpStatus.CONFLICT);
    }
}
