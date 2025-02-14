package com.hospitalmanagement.application.controller;

import com.hospitalmanagement.application.dto.DoctorDto;
import com.hospitalmanagement.application.dto.DoctorRegistrationDto;
import com.hospitalmanagement.application.dto.MedicalRecordDto;
import com.hospitalmanagement.application.model.Doctor;
import com.hospitalmanagement.application.repository.DoctorRepository;
import com.hospitalmanagement.application.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<DoctorDto>> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    @PostMapping("/new")
    public ResponseEntity<Object> newDoctor(@RequestBody DoctorRegistrationDto doctorRegistrationDto){
        return doctorService.createNewDoctor(doctorRegistrationDto);
    }


    @GetMapping("/generate_report/{userId}")
    public ResponseEntity<MedicalRecordDto> generateMedicalRecord(
            @PathVariable Integer userId
    ){
        return doctorService.createMedicalRecord(userId);
    }
}
