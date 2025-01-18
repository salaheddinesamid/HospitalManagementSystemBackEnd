package com.hospitalmanagement.application.service;

import com.hospitalmanagement.application.repository.*;
import org.springframework.stereotype.Service;


@Service
public class AllocationService {

    private final AllocationRepository allocationRepository;
    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;
    private final BillRepository billRepository;
    private final PatientRepository patientRepository;

    public AllocationService(AllocationRepository allocationRepository, HistoryRepository historyRepository, UserRepository userRepository, BillRepository billRepository, PatientRepository patientRepository) {
        this.allocationRepository = allocationRepository;
        this.historyRepository = historyRepository;
        this.userRepository = userRepository;
        this.billRepository = billRepository;
        this.patientRepository = patientRepository;
    }

    /*
    TO BE IMPLEMENTED
    public ResponseEntity<Allocation> createAllocation(AllocationDto allocationDto){
        Allocation allocation = new Allocation();
        History history = new History();
        Bill bill = new Bill();

        allocation.setName(allocationDto.getName());
        allocation.setNationalId(allocationDto.getNationalId());
        allocation.setRoomNumber(allocationDto.getRoomNumber());
        bill.setDate(new Date());
        bill.setAmount();

    }*/
}
