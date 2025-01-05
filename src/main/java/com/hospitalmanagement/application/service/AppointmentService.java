package com.hospitalmanagement.application.service;

import com.hospitalmanagement.application.dto.AppointmentDto;
import com.hospitalmanagement.application.model.Appointment;
import com.hospitalmanagement.application.model.Patient;
import com.hospitalmanagement.application.repository.AppointmentRepository;
import com.hospitalmanagement.application.repository.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {


    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
    }

    public void createPatientAutomatically(AppointmentDto appointmentDto){
        Patient patient = new Patient();
        patient.setFirstName(appointmentDto.getFirstName());
        patient.setLastName(appointmentDto.getLastName());
        patient.setAddress(appointmentDto.getAddress());
        patient.setNationalId(appointmentDto.getNationalId());
        patient.setEmail(appointmentDto.getEmail());
        patientRepository.save(patient);
    }

    public ResponseEntity<Object> createAppointment(AppointmentDto appointmentDto){
        Appointment appointment = new Appointment();
        Patient patient = patientRepository.findByEmail(appointmentDto.getEmail());
        if (!patientRepository.existsByEmail(appointmentDto.getEmail())) {
            createPatientAutomatically(appointmentDto);
            //Patient patient = patientRepository.findByEmail(appointmentDto.getEmail());
        }
        appointment.setPatient(patient);
        appointment.setDate(appointmentDto.getDate());
        appointment.setDoctor(appointmentDto.getDoctor());
        appointment.setTime(appointmentDto.getTime());
        appointment.setStatus(appointmentDto.getStatus());
        appointmentRepository.save(appointment);

        return new ResponseEntity<>("APPOINTMENT CREATED", HttpStatus.OK);
    }
}
