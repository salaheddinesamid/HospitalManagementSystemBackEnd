package com.hospitalmanagement.application.service;

import com.hospitalmanagement.application.dto.AppointmentDto;
import com.hospitalmanagement.application.dto.PatientDto;
import com.hospitalmanagement.application.exception.AppointmentException;
import com.hospitalmanagement.application.model.*;
import com.hospitalmanagement.application.repository.*;
import org.apache.juli.logging.Log;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
public class AppointmentService {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(AppointmentService.class);
    Logger logger = Logger.getLogger(AppointmentService.class.getName());


    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DiseaseRepository diseaseRepository;
    private final BillRepository billRepository;
    private final PasswordEncoder passwordEncoder;
    private  DoctorRepository doctorRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository,
                              PatientRepository patientRepository,
                              DiseaseRepository diseaseRepository,
                              BillService billService,
                              BillRepository billRepository, PasswordEncoder passwordEncoder,
                              DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.diseaseRepository = diseaseRepository;
        //this.billService = billService;
        this.billRepository = billRepository;
        this.passwordEncoder = passwordEncoder;
        this.doctorRepository = doctorRepository;
    }
    /* TO BE REVIEWED
    // Permanent and does not use the app
    public void createPatientAutomatically(AppointmentDto appointmentDto){
        Patient patient = new Patient();
        patient.setFirstName(appointmentDto.getFirstName());
        patient.setLastName(appointmentDto.getLastName());
        patient.setAddress(appointmentDto.getAddress());
        patient.setNationalId(appointmentDto.getNationalId());
        patient.setEmail(appointmentDto.getEmail());
        patientRepository.save(patient);
    }*/


    public Integer getDiseasePrice(String disease){
        Disease d = diseaseRepository.findByName(disease);
        return d.getPrice();
    }

    public ResponseEntity<List<Appointment>> getAllAppointment(){
        return new ResponseEntity<>(appointmentRepository.findAll(), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> createAppointment(AppointmentDto appointmentDto){
        String patientNationalId = appointmentDto.getPatientDto().getNationalId();
        logger  .info("Checking user existence, national id: " + patientNationalId);
        if (!patientRepository.existsByNationalId(
                patientNationalId
        )){

            logger.fine("Creating new patient");
            Patient patient = new Patient();
            PatientDto patientDto = appointmentDto.getPatientDto();
            patient.setNationalId(patientDto.getNationalId());
            patient.setFirstName(patientDto.getFirstName());
            patient.setLastName(patientDto.getLastName());
            patient.setEmail(patientDto.getEmail());
            // Generate initial password for on-site and first time patients, so they can access the application
            String password = patient.getLastName() + patient.getNationalId().hashCode();
            patient.setPassword(passwordEncoder.encode(password));

            // Save the patient
            patientRepository.save(patient);
        }
        Patient patient = patientRepository.findByNationalId(patientNationalId);
        logger.info("Patient exists with national id: " + patientNationalId);
        Appointment appointment = new Appointment();
        Bill bill = new Bill();
        appointment.setDate(appointmentDto.getSelectedDate());
        appointment.setStatus("IN REVIEW");
        appointment.setDisease(appointmentDto.getDisease());

        bill.setAmount(appointmentDto.getPrice());
        bill.setStatus("UNPAID");
        bill.setPatient(patient);
        bill.setDate(new Date(System.currentTimeMillis()));


        appointmentRepository.save(appointment);
        billRepository.save(bill);

        return new ResponseEntity<>("Appointment has been created", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Object> deleteAppointment(Integer appointmentId){
        try{
            Bill bill = billRepository.findByAppointmentId(appointmentId);
            logger.info("Deleting the appointment...");
            appointmentRepository.deleteById(appointmentId);
            logger.fine("Appointment deleted successfully");
            logger.info("Deleting the corresponding bill...");
            billRepository.delete(bill);
            logger.info("Bill deleted successfully");
            return new ResponseEntity<>("Appointment Deleted Successfully"
            ,HttpStatus.OK);
        }catch  (AppointmentException e){
            throw new AppointmentException();
        }
    }
}
