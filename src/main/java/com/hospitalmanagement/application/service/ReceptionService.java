package com.hospitalmanagement.application.service;


import com.hospitalmanagement.application.dto.AppointmentDto;
import com.hospitalmanagement.application.dto.PatientDto;
import com.hospitalmanagement.application.model.*;
import com.hospitalmanagement.application.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReceptionService {

    private final AppointmentRepository appointmentRepository;
    //private final PatientService patientService;
    private final PatientRepository patientRepository;
    private final ContactRepository contactRepository;
    private final ContactPatientRepository contactPatientRepository;
    //private final RoomRepository roomRepository;

    public ReceptionService(AppointmentRepository appointmentRepository, PatientService patientService, PatientRepository patientRepository, ContactRepository contactRepository, ContactPatientRepository contactPatientRepository, RoomRepository roomRepository) {
        this.appointmentRepository = appointmentRepository;
        //this.patientService = patientService;
        this.patientRepository = patientRepository;
        this.contactRepository = contactRepository;
        this.contactPatientRepository = contactPatientRepository;
        //this.roomRepository = roomRepository;
    }

    public ResponseEntity<Object> scheduleAppointment(AppointmentDto appointmentDto){
        Patient patient = new Patient(appointmentDto.getFirstName(),
                appointmentDto.getLastName(),appointmentDto.getNationalId(),
                appointmentDto.getAddress());

        if(patientRepository.existsByFirstNameAndLastName(patient.getFirstName(),
                patient.getLastName())){
            Appointment appointment = new Appointment();
            Bill bill = new Bill();
            appointment.setPatient(patient);
            appointment.setDate(appointmentDto.getDate());
            appointment.setDoctor(appointmentDto.getDoctor());
            appointment.setTime(appointmentDto.getTime());
            bill.setDate(appointmentDto.getDate());
            bill.setPatient(patient);
            bill.setAmount(300);
            bill.setStatus("Not paid");
            Room room = new Room(appointmentDto.getRoomNumber());
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
    public ResponseEntity<Object> registerPatient(PatientDto patientDto){
        Patient patient = new Patient(patientDto.getFirstName(),
                patientDto.getLastName(),patientDto.getNationalId(),patientDto.getAddress());
        Contact contact = new Contact();
        ContactPatient contactPatient = new ContactPatient();
        contact.setPhone(patientDto.getPhone());
        contact.setEmail(patientDto.getEmail());
        contactPatient.setContact(contact);
        contactPatient.setPatient(patient);
        contactRepository.save(contact);
        contactPatientRepository.save(contactPatient);
        return new ResponseEntity<>("Patient saved", HttpStatus.OK);
    }
}
