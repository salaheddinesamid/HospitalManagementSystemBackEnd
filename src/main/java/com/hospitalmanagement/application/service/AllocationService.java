package com.hospitalmanagement.application.service;
import com.hospitalmanagement.application.dto.AllocationDto;
import com.hospitalmanagement.application.model.Allocation;
import com.hospitalmanagement.application.model.Room;
import com.hospitalmanagement.application.repository.AllocationRepository;
import com.hospitalmanagement.application.repository.PatientRepository;
import com.hospitalmanagement.application.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class AllocationService {

    private final AllocationRepository allocationRepository;
    private final RoomRepository roomRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public AllocationService(
            AllocationRepository allocationRepository,
            RoomRepository roomRepository, PatientRepository patientRepository
    ){
        this.allocationRepository = allocationRepository;
        this.roomRepository = roomRepository;
        this.patientRepository = patientRepository;
    }

    public ResponseEntity<?> allocate(AllocationDto allocationDto){
        Allocation allocation = new Allocation();
        Room room = roomRepository.findById(allocationDto.getRoomId()).get();

        if()
    }

}
