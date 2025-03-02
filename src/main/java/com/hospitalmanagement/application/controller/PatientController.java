package com.hospitalmanagement.application.controller;

import com.hospitalmanagement.application.dto.LoginDTO;
import com.hospitalmanagement.application.dto.PatientDto;
import com.hospitalmanagement.application.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<PatientDto>> allPatients(){
        return patientService.getAllPatients();
    }

    @PostMapping("/register")
    public ResponseEntity<Object> newPatient(@RequestBody PatientDto newPatientDto){
        return patientService.registerPatient(newPatientDto);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(LoginDTO loginDTO){
        return patientService
    }
}
