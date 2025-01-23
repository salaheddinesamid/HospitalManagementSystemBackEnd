package com.hospitalmanagement.application.service;

import com.hospitalmanagement.application.dto.AllocationDto;
import com.hospitalmanagement.application.model.Allocation;
import com.hospitalmanagement.application.model.Bill;
import com.hospitalmanagement.application.model.Patient;
import com.hospitalmanagement.application.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class AllocationService {

    private final AllocationRepository allocationRepository;
    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;
    private final BillService billService;
    //private final RoomRepository roomRepository;
    private final RoomService roomService;
    private final PatientRepository patientRepository;
    private final PatientService patientService;

    public AllocationService(AllocationRepository allocationRepository, HistoryRepository historyRepository, UserRepository userRepository, BillRepository billRepository, BillService billService, RoomService roomService, PatientRepository patientRepository, PatientService patientService) {
        this.allocationRepository = allocationRepository;
        this.historyRepository = historyRepository;
        this.userRepository = userRepository;
        this.billService = billService;
        this.billRepository = billRepository;
        this.roomService = roomService;
        //this.roomRepository = roomRepository;
        this.patientRepository = patientRepository;
        this.patientService = patientService;
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

    public ResponseEntity<Object> createAllocation(
            AllocationDto allocationDto
    ){
        try {
            Allocation allocation =  new Allocation();
            Bill bill = new Bill();
            boolean patientExists = patientRepository.existsByNationalId(
                    allocationDto.getNationalId()
            );
            if (patientExists){

            }else {
                Patient patient = new Patient();
                patient.setFirstName(
                        allocationDto.getFirstName()
                );
                patient.setLastName(
                        allocationDto.getLastName()
                );

                patient.setNationalId(
                        allocationDto.getNationalId()
                );
            }
        } catch ()
    }
}
