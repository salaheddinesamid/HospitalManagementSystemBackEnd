package com.hospitalmanagement.application.service;


import com.hospitalmanagement.application.dto.AllocationDto;
import com.hospitalmanagement.application.model.Allocation;
import com.hospitalmanagement.application.repository.AllocationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AllocationService {

    private final AllocationRepository allocationRepository;

    public AllocationService(AllocationRepository allocationRepository) {
        this.allocationRepository = allocationRepository;
    }

    public ResponseEntity<Object> createAllocation(AllocationDto allocationDto){
        Allocation allocation = new Allocation();
        allocation.setRoomNumber(allocationDto.getRoomNumber());
        //allocation.setPatient(allocationDto.getPatientDto());
        allocation.setName(allocationDto.getName());
        allocationRepository.save(allocation);
        return new ResponseEntity<>("Allocation Saved", HttpStatus.OK);
    }

}
