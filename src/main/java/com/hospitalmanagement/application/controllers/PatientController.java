package com.hospitalmanagement.application.controllers;

import com.hospitalmanagement.application.models.Patient;
import com.hospitalmanagement.application.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("patient")
public class PatientController {
    private final PatientService patientService;
    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }
    @PostMapping("/new")
    ResponseEntity<Object> newPatient(@RequestBody Patient patient){
        return patientService.addPatient(patient);
    }
}
