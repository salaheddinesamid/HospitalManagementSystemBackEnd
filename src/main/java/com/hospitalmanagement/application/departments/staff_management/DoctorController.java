package com.hospitalmanagement.application.departments.staff_management;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("doctor")
public class DoctorController {
    private final DoctorService doctorService;
    public DoctorController(DoctorService doctorService){
        this.doctorService  = doctorService;
    }
    @GetMapping("/")
    List<Doctor> listOfDoctors(){
        return doctorService.getAllDoctors();
    }
    @PostMapping("/new")
    public ResponseEntity<Object> newDoctor(@RequestBody Doctor doctor){
        return doctorService.addNewDoctor(doctor);
    }
}
