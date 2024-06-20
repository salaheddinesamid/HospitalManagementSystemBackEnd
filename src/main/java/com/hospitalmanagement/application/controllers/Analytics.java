package com.hospitalmanagement.application.controllers;

import com.hospitalmanagement.application.departments.appointment_management.AppointmentService;
import com.hospitalmanagement.application.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("analytics")
@CrossOrigin
public class Analytics {
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    PatientService patientService;

    @GetMapping("/total_patient")
    public long getTotalPatient(){
        return patientService.getTotalPatients();
    }
    @GetMapping("/total_appointment")
    public long getTotalAppointment(){
        return appointmentService.getTotal();
    }
}
