package com.hospitalmanagement.application.controller;

import com.hospitalmanagement.application.dto.NewPatientDto;
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

    @PostMapping("/new")
    public ResponseEntity<Object> newPatient(@RequestBody NewPatientDto newPatientDto){
        return patientService.registerPatient(newPatientDto);
    }
}
