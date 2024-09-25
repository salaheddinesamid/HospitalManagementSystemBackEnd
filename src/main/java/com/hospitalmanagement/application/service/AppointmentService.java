package com.hospitalmanagement.application.service;


import com.hospitalmanagement.application.dto.AppointmentDto;
import com.hospitalmanagement.application.model.Appointment;
import com.hospitalmanagement.application.model.Patient;
import com.hospitalmanagement.application.repository.AppointmentRepository;
import com.hospitalmanagement.application.repository.PatientRepository;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientService patientService;
    private final PatientRepository patientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, PatientService patientService, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientService = patientService;
        this.patientRepository = patientRepository;
    }

    public ResponseEntity<Object> scheduleAppointment(AppointmentDto appointmentDto){
        Patient patient = new Patient(appointmentDto.getFirstName(),
                appointmentDto.getLastName(),appointmentDto.getNationalId(),
                appointmentDto.getAddress());

        if(patientRepository.existsByFirstNameAndLastName(patient.getFirstName(),
                patient.getLastName())){
            Appointment appointment = new Appointment();
            appointment.setPatient(patient);
            appointment.setDate(appointmentDto.getDate());
            appointment.setDoctor(appointmentDto.getDoctor());
            appointment.setTime(appointmentDto.getTime());
            appointmentRepository.save(appointment);
            return new ResponseEntity<>("Appointment created successfully", HttpStatus.OK);
        }else{
            Patient newPatient  = new Patient();
            patient.setFirstName(appointmentDto.getFirstName());
            patient.setLastName(appointmentDto.getLastName());
            patient.setNationalId(appointmentDto.getNationalId());
            patient.setAddress(appointmentDto.getAddress());
            patientRepository.save(newPatient);
            Appointment appointment = new Appointment();
            appointment.setPatient(patient);
            appointment.setDate(appointmentDto.getDate());
            appointment.setDoctor(appointmentDto.getDoctor());
            appointment.setTime(appointmentDto.getTime());
            appointmentRepository.save(appointment);
            return new ResponseEntity<>("Appointment created successfully", HttpStatus.OK);
        }

    }
}
