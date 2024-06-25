package com.hospitalmanagement.application.departments.staff_management;

import com.hospitalmanagement.application.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/patient-staff")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/total")
    public String test() {
        return "Hello, Good evening";
    }
}
