package com.hospitalmanagement.application.service;
import com.hospitalmanagement.application.dto.AllocationDto;
import com.hospitalmanagement.application.model.Allocation;
import com.hospitalmanagement.application.model.Patient;
import com.hospitalmanagement.application.model.Role;
import com.hospitalmanagement.application.model.Room;
import com.hospitalmanagement.application.repository.AllocationRepository;
import com.hospitalmanagement.application.repository.PatientRepository;
import com.hospitalmanagement.application.repository.RoleRepository;
import com.hospitalmanagement.application.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AllocationService {

    private final AllocationRepository allocationRepository;
    private final RoomRepository roomRepository;
    private final PatientRepository patientRepository;
    private final PatientService patientService;

    @Autowired
    public AllocationService(
            AllocationRepository allocationRepository,
            RoomRepository roomRepository, PatientRepository patientRepository, PatientService patientService
    ){
        this.allocationRepository = allocationRepository;
        this.roomRepository = roomRepository;
        this.patientRepository = patientRepository;
        this.patientService = patientService;
    }


    @Transactional
    @Async
    public ResponseEntity<?> allocate(AllocationDto allocationDto){
        Allocation allocation = new Allocation();
        Patient patient;
        Room room = roomRepository.findById(allocationDto.getRoomId()).get();
        String nationalId = allocationDto.getPatientDto().getNationalId();
        if(patientRepository.existsByNationalId(nationalId)){
            patient = patientRepository.findByNationalId(nationalId);
        }else{
            patientService.registerPatient(allocationDto.getPatientDto());
            patient = patientRepository.findByNationalId(nationalId);
        }
        allocation.setPatient(patient);
        allocation.setRoom(room);
        room.setStatus("unavailable");
        room.setPatient(patient);
        allocationRepository.save(allocation);
        roomRepository.save(room);
        return new ResponseEntity<>("The room has been allocated successfully", HttpStatus.OK);
    }

}
