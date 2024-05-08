package com.hospitalmanagement.application.services;

import com.hospitalmanagement.application.models.Nurse;
import com.hospitalmanagement.application.repositories.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NurseService {
    @Autowired
    NurseRepository nurseRepository;

    public ResponseEntity<Object> addNurse(Nurse nurse){
        nurseRepository.save(nurse);
        return new ResponseEntity<>("Nurse added successfully", HttpStatus.OK);
    }
}
