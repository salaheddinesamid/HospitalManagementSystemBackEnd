package com.hospitalmanagement.application.service;

import com.hospitalmanagement.application.dto.DoctorDto;
import com.hospitalmanagement.application.dto.DoctorRegistrationDto;
import com.hospitalmanagement.application.dto.MedicalRecordDto;
import com.hospitalmanagement.application.exception.UserAlreadyExistsException;
import com.hospitalmanagement.application.model.Appointment;
import com.hospitalmanagement.application.model.Doctor;
import com.hospitalmanagement.application.model.MedicalRecord;
import com.hospitalmanagement.application.model.Patient;
import com.hospitalmanagement.application.repository.AppointmentRepository;
import com.hospitalmanagement.application.repository.DoctorRepository;
import com.hospitalmanagement.application.repository.MedicalRecordRepository;
import com.hospitalmanagement.application.repository.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;


@Service
public class DoctorService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;

    public DoctorService(MedicalRecordRepository medicalRecordRepository, PatientRepository patientRepository, DoctorRepository doctorRepository,AppointmentRepository appointmentRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public ResponseEntity<Object> createNewDoctor(DoctorRegistrationDto doctorRegistrationDto){
        if(!doctorRepository.existsByNationalId(doctorRegistrationDto.getNationalId())){
            Doctor doctor = new Doctor();
            doctor.setAge(doctorRegistrationDto.getAge());
            doctor.setNationalId(doctorRegistrationDto.getNationalId());
            doctor.setBirth(doctorRegistrationDto.getBirthday());
            doctor.setSpecialization(doctorRegistrationDto.getSpecialization());
            doctor.setYearsOfExperience(doctorRegistrationDto.getYearsOfExperience());
            doctor.setSpecialization(doctorRegistrationDto.getSpecialization());
            doctor.setFirstName(doctorRegistrationDto.getFirstName());
            doctor.setLastName(doctorRegistrationDto.getLastName());
            doctorRepository.save(doctor);
            return new ResponseEntity<>("Doctor Created",HttpStatus.OK);
        }else{
            throw  new UserAlreadyExistsException();
        }
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

    public ResponseEntity<List<DoctorDto>> getAllDoctors(){
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorDto> doctorDtoList = doctors
                .stream().map(
                        doctor -> {
                            DoctorDto doctorDto = new DoctorDto();
                            doctorDto.setFullName(doctor.getFirstName() + " " + doctor.getLastName());
                            doctorDto.setProfessionalId(doctor.getProfessionalId());
                            doctorDto.setAge(doctor.getAge());
                            doctorDto.setSpecialization(doctor.getSpecialization());
                            doctorDto.setProfessionalId(doctor.getProfessionalId());
                            return  doctorDto;
                        }
                ).toList();

        return new ResponseEntity<>(doctorDtoList,HttpStatus.OK);
    }

    public ResponseEntity<Object> completeAppointment(
            Integer appointmentId
    ){
        Appointment appointment = appointmentRepository.findById(appointmentId).get();
        appointment.setStatus("Completed");
        createMedicalRecord()
    }
}
