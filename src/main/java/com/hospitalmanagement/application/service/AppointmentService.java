package com.hospitalmanagement.application.service;

import com.hospitalmanagement.application.dto.AppointmentDto;
import com.hospitalmanagement.application.exception.AppointmentException;
import com.hospitalmanagement.application.model.*;
import com.hospitalmanagement.application.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AppointmentService {


    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DiseaseRepository diseaseRepository;
    //private final BillService billService;
    private final BillRepository billRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository, DiseaseRepository diseaseRepository, BillService billService, BillRepository billRepository, UserRepository userRepository, DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.diseaseRepository = diseaseRepository;
        //this.billService = billService;
        this.billRepository = billRepository;
        this.userRepository = userRepository;

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

    public ResponseEntity<Object> cancelUserAppointment(
            Integer appointmentId
    ){
        appointmentRepository.deleteById(appointmentId);
        return new ResponseEntity<>("Appointment canceled ",HttpStatus.OK);
    }

    @Async
    public ResponseEntity<Object> createAppointment(AppointmentDto appointmentDto){
        Appointment appointment = new Appointment();
        Bill bill = new Bill();
        Doctor doctor = doctorRepository.findById(appointmentDto.getDoctor()).get();
        User user   = userRepository.findById(appointmentDto.getUserId()).get();
        appointment.setUser(user);
        appointment.setDate(appointmentDto.getDate());
        appointment.setDoctor(doctor);
        appointment.setTime(appointmentDto.getTime());
        appointment.setStatus(appointmentDto.getStatus());
        bill.setAmount(getDiseasePrice(appointmentDto.getDisease()));
        bill.setDate(new Date());
        bill.setStatus("Unpaid");
        billRepository.save(bill);
        appointmentRepository.save(appointment);
        return new ResponseEntity<>("APPOINTMENT CREATED", HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteAppointment(Integer appointmentId){
        try{
            appointmentRepository.deleteById(appointmentId);
            return new ResponseEntity<>("Appointment Deleted Successfully"
            ,HttpStatus.OK);
        }catch  (AppointmentException e){
            throw new AppointmentException();
        }
    }
}
