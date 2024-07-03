package com.hospitalmanagement.application.departments.staff_management;

import com.hospitalmanagement.application.models.Patient;
import com.hospitalmanagement.application.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/patient-management")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("get_all")
    public List<Patient> getAll(){
        return patientService.getAllPatient();
    }


    @PostMapping("new_patient")
    ResponseEntity<Object> newPatient(@RequestBody Patient patient){
        return patientService.addPatient(patient);
    }
    @GetMapping("/total")
    public String test() {
        return "Hello, Good evening";
    }
}
