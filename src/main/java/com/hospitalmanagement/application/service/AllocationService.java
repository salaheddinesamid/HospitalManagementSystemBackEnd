package com.hospitalmanagement.application.service;


import com.hospitalmanagement.application.dto.AllocationDto;
import com.hospitalmanagement.application.model.Allocation;
import com.hospitalmanagement.application.model.Bill;
import com.hospitalmanagement.application.model.History;
import com.hospitalmanagement.application.model.User;
import com.hospitalmanagement.application.repository.AllocationRepository;
import com.hospitalmanagement.application.repository.BillRepository;
import com.hospitalmanagement.application.repository.HistoryRepository;
import com.hospitalmanagement.application.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AllocationService {

    private final AllocationRepository allocationRepository;
    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;
    private final BillRepository billRepository;

    public AllocationService(AllocationRepository allocationRepository, HistoryRepository historyRepository, UserRepository userRepository, BillRepository billRepository) {
        this.allocationRepository = allocationRepository;
        this.historyRepository = historyRepository;
        this.userRepository = userRepository;
        this.billRepository = billRepository;
    }

    public ResponseEntity<Allocation> createAllocation(AllocationDto allocationDto){
        Allocation allocation = new Allocation();
        History history = new History();
        Bill bill = new Bill();
        allocation.setRoomNumber(allocationDto.getRoomNumber());
        //allocation.setPatient(allocationDto.getPatientDto());
        allocation.setName(allocationDto.getName());
        bill.setStatus("UNPAID");
        bill.setAmount(100);
        bill.setDate(new Date());
        history.setAction("ALLOCATION RESERVED, ROOM NUMBER:" + allocationDto.getRoomNumber());
        history.setUser(userRepository.findById(allocationDto.getReceptionistId()).get());
        history.setDate(new Date());
        historyRepository.save(history);
        allocationRepository.save(allocation);
        billRepository.save(bill);
        return new ResponseEntity<>(allocation, HttpStatus.OK);
    }
}
