package com.hospitalmanagement.application.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RoomExceptionController{


    @ExceptionHandler(value = PatientRoomNotFoundException.class)
    public ResponseEntity<Object> exception(PatientRoomNotFoundException exception){
        return new ResponseEntity<>("No room reserved for this patient", HttpStatus.NOT_FOUND);
    }
}
