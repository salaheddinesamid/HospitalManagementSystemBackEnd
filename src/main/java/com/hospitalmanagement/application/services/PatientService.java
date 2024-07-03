package com.hospitalmanagement.application.services;

import com.hospitalmanagement.application.departments.staff_management.HistoryService;
import com.hospitalmanagement.application.models.Patient;
import com.hospitalmanagement.application.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;
    private final HistoryService historyService;

    public PatientService(HistoryService historyService) {
        this.historyService = historyService;
    }

    public ResponseEntity<Object> addPatient(Patient patient){
        if(patientRepository.existsByNationalId(patient.getNationalId())){
            return new ResponseEntity<>("Sorry, this patient is already exists..", HttpStatus.ALREADY_REPORTED);
        }else{
            patientRepository.save(patient);
            historyService.addHistory("Added patient: " + patient.getFullName());
            return new ResponseEntity<>("The patient has been added successfully",HttpStatus.OK);
        }
    }

    public List<Patient> getAllPatient(){
        return patientRepository.findAll();
    }
    public long getTotalPatients(){
        return patientRepository.count();
    }
}
