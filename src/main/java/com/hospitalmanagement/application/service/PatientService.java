package com.hospitalmanagement.application.service;

import com.hospitalmanagement.application.dto.NewPatientDto;
import com.hospitalmanagement.application.dto.PatientDto;
import com.hospitalmanagement.application.model.Contact;
import com.hospitalmanagement.application.model.ContactPatient;
import com.hospitalmanagement.application.model.Patient;
import com.hospitalmanagement.application.repository.ContactPatientRepository;
import com.hospitalmanagement.application.repository.ContactRepository;
import com.hospitalmanagement.application.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    private final ContactRepository contactRepository;
    private final ContactPatientRepository contactPatientRepository;

    public PatientService(ContactRepository contactRepository, ContactPatientRepository contactPatientRepository) {
        this.contactRepository = contactRepository;
        this.contactPatientRepository = contactPatientRepository;
    }

    @Transactional
    public ResponseEntity<Object> registerPatient(NewPatientDto newPatientDto) {
        Patient patient = new Patient();
        //Contact contact = new Contact();
        ContactPatient contactPatient = new ContactPatient();
        if (!(patientRepository.existsByFirstNameAndLastName(newPatientDto.getFirstName(), newPatientDto.getLastName()))) {
            patient.setFirstName(newPatientDto.getFirstName());
            patient.setLastName(newPatientDto.getLastName());
            patient.setAddress(newPatientDto.getAddress());
            patient.setNationalId(newPatientDto.getNationalId());
            patientRepository.save(patient);
        } else {
            return new ResponseEntity<>("Patient already exists", HttpStatus.ALREADY_REPORTED);

        }
        return new ResponseEntity<>("Patient created", HttpStatus.OK);
    }

    public ResponseEntity<List<PatientDto>> getAllPatients(){
        List<Patient> patients = patientRepository.findAll();
        List<PatientDto> patientDtoList = patients
                .stream().map(patient -> {
                    PatientDto patientDto = new PatientDto();
                    patientDto.setFullName(patient.getFirstName() + " " + patient.getLastName());
                    patientDto.setAddress(patient.getAddress());
                    patientDto.setEmail(patient.getEmail());
                    patientDto.setNationalId(patient.getNationalId());
                    return patientDto;
                }).toList();
        return new ResponseEntity<>(patientDtoList,HttpStatus.OK);
    }
}
