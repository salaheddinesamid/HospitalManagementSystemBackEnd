package com.hospitalmanagement.application.service;
import com.hospitalmanagement.application.dto.AllocationDto;
import com.hospitalmanagement.application.dto.AllocationResponseDto;
import com.hospitalmanagement.application.dto.PatientDto;
import com.hospitalmanagement.application.dto.RoomDetailsDto;
import com.hospitalmanagement.application.model.Allocation;
import com.hospitalmanagement.application.model.Patient;
import com.hospitalmanagement.application.model.Room;
import com.hospitalmanagement.application.repository.AllocationRepository;
import com.hospitalmanagement.application.repository.PatientRepository;
import com.hospitalmanagement.application.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;


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

    @Cacheable(value = "allocation")
    @Transactional
    public CompletableFuture<ResponseEntity<?>> allocate(AllocationDto allocationDto){
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
        return CompletableFuture.completedFuture(new ResponseEntity<>("The room has been allocated successfully", HttpStatus.OK));
    }

    @Cacheable(value = "allocations")
    public ResponseEntity<List<AllocationResponseDto>> getAllocations(){
        List<Allocation> allocations = allocationRepository.findAll();
        List<AllocationResponseDto> allocationDto =
                allocations.
                        stream()
                        .map(allocation -> {
                            AllocationResponseDto dto = new AllocationResponseDto();
                            Room room = allocation
                                    .getRoom();
                            RoomDetailsDto roomDetailsDto = new RoomDetailsDto();
                            Patient patient = allocation.getPatient();
                            roomDetailsDto.setRoomNumber(room.getRoomNumber());
                            PatientDto patientDto = new PatientDto();
                            patientDto.setFirstName(patient.getFirstName());
                            patientDto.setLastName(patient.getLastName());
                            patientDto.setNationalId(patient.getNationalId());
                            dto.setAllocationId(allocation.getAllocationId());
                            dto.setDate(allocation.getDate());
                            dto.setStatus(allocation.getStatus());
                            dto.setRoom(roomDetailsDto);
                            dto.setPatientDto(patientDto);
                            return dto;
                        }).toList();

        return new ResponseEntity<>(allocationDto,HttpStatus.OK);
    }

}
