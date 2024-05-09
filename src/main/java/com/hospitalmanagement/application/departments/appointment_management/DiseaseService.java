package com.hospitalmanagement.application.departments.appointment_management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DiseaseService {
    @Autowired
    DiseaseRepository diseaseRepository;
    public ResponseEntity<Object> addNewDisease(Disease disease){
        diseaseRepository.save(disease);
        return new ResponseEntity<>("Disease has been added", HttpStatus.OK);
    }
}
