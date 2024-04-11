package com.hospitalmanagement.application.services;

import com.hospitalmanagement.application.models.Doctor;
import com.hospitalmanagement.application.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }
    public ResponseEntity<Object> addNewDoctor(Doctor doctor){
        if(doctorRepository.existsByFullName(doctor.getFullName())){
            return new ResponseEntity<>("This doctor is already exists",HttpStatus.ALREADY_REPORTED);
        }else{
            doctorRepository.save(doctor);
            return new ResponseEntity<>("The doctor has been added successfully",HttpStatus.OK);
        }
    }
}
