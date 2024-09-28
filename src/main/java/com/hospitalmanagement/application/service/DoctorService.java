package com.hospitalmanagement.application.service;

import com.hospitalmanagement.application.dto.MedicalRecordDto;
import com.hospitalmanagement.application.model.MedicalRecord;
import com.hospitalmanagement.application.model.Patient;
import com.hospitalmanagement.application.repository.MedicalRecordRepository;
import com.hospitalmanagement.application.repository.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class DoctorService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;

    public DoctorService(MedicalRecordRepository medicalRecordRepository, PatientRepository patientRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.patientRepository = patientRepository;
    }

    public ResponseEntity<Object> createMedicalRecord(MedicalRecordDto medicalRecordDto){
        if(!patientRepository.existsByFirstNameAndLastName(medicalRecordDto.getPatient().getFirstName(),
                medicalRecordDto.getPatient().getLastName())){
            Patient patient = new Patient();
            MedicalRecord medicalRecord = new MedicalRecord(medicalRecordDto.getPatient(),
                    medicalRecordDto.getDoctor(),medicalRecordDto.getDiagnosis(),
                    medicalRecordDto.getTestResult(),medicalRecordDto.getDate(),medicalRecordDto.getNotes());
            patient.setFirstName(medicalRecordDto.getPatient().getFirstName());
            patient.setLastName(medicalRecordDto.getPatient().getLastName());
            patientRepository.save(patient);
            medicalRecordRepository.save(medicalRecord);
        }
        return new ResponseEntity<>("Medical record has been created", HttpStatus.OK);
    }
}
