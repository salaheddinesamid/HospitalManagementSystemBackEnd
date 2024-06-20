package com.hospitalmanagement.application.services;

import com.hospitalmanagement.application.controllers.PatientController;
import com.hospitalmanagement.application.models.Patient;
import com.hospitalmanagement.application.repositories.PatientRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;
    public ResponseEntity<Object> addPatient(Patient patient){
        if(patientRepository.existsByNationalId(patient.getNationalId())){
            return new ResponseEntity<>("Sorry, this patient is already exists..", HttpStatus.ALREADY_REPORTED);
        }else{
            patientRepository.save(patient);
            return new ResponseEntity<>("The patient has been added successfully",HttpStatus.OK);
        }
    }
    public long getTotalPatients(){
        return patientRepository.count();
    }
}
