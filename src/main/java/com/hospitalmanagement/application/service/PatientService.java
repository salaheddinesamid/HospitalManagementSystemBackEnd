package com.hospitalmanagement.application.service;

import com.hospitalmanagement.application.dto.LoginDTO;
import com.hospitalmanagement.application.dto.PatientDetailsDto;
import com.hospitalmanagement.application.dto.PatientDto;
import com.hospitalmanagement.application.dto.TokenDTO;
import com.hospitalmanagement.application.exception.PatientNotFoundException;
import com.hospitalmanagement.application.jwt.JwtUtil;
import com.hospitalmanagement.application.model.Patient;
import com.hospitalmanagement.application.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class PatientService {


    private PatientRepository patientRepository;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;


    @Autowired
    public PatientService(PatientRepository patientRepository,
                          AuthenticationManager authenticationManager,
                          PasswordEncoder passwordEncoder,
                          JwtUtil jwtUtil
                          ) {
        this.patientRepository = patientRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // Register new patient:
    @Transactional
        public ResponseEntity<Object> registerPatient(PatientDto newPatientDto) {
        Patient patient = new Patient();
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

    // Patient Authentication:
    public ResponseEntity<?> authentication(LoginDTO loginDTO){
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword())
                );

        try{

            Patient patient = patientRepository.findByEmail(loginDTO.getEmail());

            if (patient.getPassword().equals(passwordEncoder.encode(patient.getPassword()))){
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String token = jwtUtil.generateToken(loginDTO.getEmail(),patient.getRole().getRoleName().toString());

                return new ResponseEntity<>(new TokenDTO(token),HttpStatus.OK);
            }else{
                return new ResponseEntity<>("BAD CREDENTIALS",HttpStatus.BAD_REQUEST);
            }
        }catch (RuntimeException e){
            throw new PatientNotFoundException();
        }
    }

    // Check if the patient already exists (by national id):
    public boolean checkPatientExistence(String nationalId){
        return patientRepository.existsByNationalId(nationalId);
    }

    // Return all registered patients
    @Cacheable(value = "patients")
    public ResponseEntity<List<PatientDetailsDto>> getAllPatients(){
        List<Patient> patients = patientRepository.findAll();
        List<PatientDetailsDto> patientsDetailsDto =
                patients.
                        stream()
                        .map(patient -> {
                            PatientDetailsDto patientDetailsDto = new PatientDetailsDto();
                            patientDetailsDto.setPatientId(patient.getId());
                            patientDetailsDto.setFullName(patient.getFirstName() + " " + patient.getLastName());
                            patientDetailsDto.setAddress(patient.getAddress());
                            patientDetailsDto.setEmail(patient.getEmail());
                            return patientDetailsDto;
                        }).toList();
        return new ResponseEntity<>(patientsDetailsDto,HttpStatus.OK);
    }
}
