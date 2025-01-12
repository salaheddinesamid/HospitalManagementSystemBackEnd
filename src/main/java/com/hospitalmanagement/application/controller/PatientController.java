package com.hospitalmanagement.application.controller;

import com.hospitalmanagement.application.dto.PatientDto;
import com.hospitalmanagement.application.model.Patient;
import com.hospitalmanagement.application.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
