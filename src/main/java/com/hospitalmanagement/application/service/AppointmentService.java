package com.hospitalmanagement.application.service;

import com.hospitalmanagement.application.dto.AppointmentDetailsDto;
import com.hospitalmanagement.application.dto.AppointmentDto;
import com.hospitalmanagement.application.dto.PatientDto;
import com.hospitalmanagement.application.exception.AppointmentException;
import com.hospitalmanagement.application.model.*;
import com.hospitalmanagement.application.repository.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final RoleRepository roleRepository;
    private  DoctorRepository doctorRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository,
                              PatientRepository patientRepository,
                              DiseaseRepository diseaseRepository,
                              //BillService billService,
                              BillRepository billRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository,
                              DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.diseaseRepository = diseaseRepository;
        //this.billService = billService;
        this.billRepository = billRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
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

    public ResponseEntity<List<AppointmentDetailsDto>> getAllAppointment(){
        List<Appointment> appointments  = appointmentRepository.findAll();

        List<AppointmentDetailsDto> appointmentDetailsDtoList =
                appointments.stream()
                        .map(appointment -> {
                            AppointmentDetailsDto appointmentDetailsDto = new AppointmentDetailsDto();
                            appointmentDetailsDto.setAppointmentId(appointment.getId());
                            appointmentDetailsDto.setPatient(appointment.getPatient());
                            appointmentDetailsDto.setDate(appointment.getDate());
                            appointmentDetailsDto.setDisease(appointment.getDisease());
                            appointmentDetailsDto.setLocation(appointment.getLocation());
                            appointmentDetailsDto.setStatus(appointment.getStatus());
                            appointmentDetailsDto.setLocation(appointment.getLocation());
                            return appointmentDetailsDto;
                        }).toList();

        return new ResponseEntity<>(appointmentDetailsDtoList,HttpStatus.OK);
    }

    @Transactional
    @Cacheable(value = "appointments")
    public ResponseEntity<?> createAppointment(AppointmentDto appointmentDto){
        String patientNationalId = appointmentDto.getPatientDto().getNationalId();
        logger  .info("Checking user existence, national id: " + patientNationalId);
        if (!patientRepository.existsByNationalId(
                patientNationalId
        )){

            logger.fine("Creating new patient");
            Patient patient = new Patient();
            Role role = roleRepository.findByRoleName(RoleName.USER);
            PatientDto patientDto = appointmentDto.getPatientDto();
            patient.setNationalId(patientDto.getNationalId());
            patient.setFirstName(patientDto.getFirstName());
            patient.setLastName(patientDto.getLastName());
            patient.setEmail(patientDto.getEmail());
            patient.setAddress(patientDto.getAddress());
            patient.setRole(role);
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
        appointment.setPatient(patient);
        appointment.setStatus("IN REVIEW");
        appointment.setDisease(appointmentDto.getDisease());
        appointment.setLocation(appointmentDto.getLocation());

        bill.setAmount(appointmentDto.getTotalPrice());
        bill.setStatus("UNPAID");
        bill.setPatient(patient);
        bill.setAppointment(appointment);
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
