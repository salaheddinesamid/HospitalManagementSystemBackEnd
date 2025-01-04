package com.hospitalmanagement.application.controller;

import com.hospitalmanagement.application.model.Doctor;
import com.hospitalmanagement.application.repository.DoctorRepository;
import com.hospitalmanagement.application.service.DoctorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/doctor")
public class DoctorController {


    private final DoctorService doctorService;

    public DoctorController(DoctorRepository doctorRepository, DoctorService doctorService) {
        this.doctorService = doctorService;
        //this.doctorRepository = doctorRepository;
    }

    @GetMapping("/")
    public List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }
}
