package com.hospitalmanagement.application.controller;

import com.hospitalmanagement.application.dto.PatientDto;
import com.hospitalmanagement.application.service.PatientService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/test")
public class Test {


    private final PatientService patientService;

    public Test(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("")
    public String test(){
        return "Server is running";
    }

    @PostMapping("register")
    public ResponseEntity<Object> register(@RequestBody PatientDto patientDto){
        return patientService.registerPatient(patientDto);

    }
}
