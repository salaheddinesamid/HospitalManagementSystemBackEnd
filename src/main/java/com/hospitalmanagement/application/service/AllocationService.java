package com.hospitalmanagement.application.service;

import com.hospitalmanagement.application.dto.AllocationDto;
import com.hospitalmanagement.application.exception.UserAlreadyExistsException;
import com.hospitalmanagement.application.model.Allocation;
import com.hospitalmanagement.application.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class AllocationService {

    private final AllocationRepository allocationRepository;
    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;
    private final BillService billService;
    private final RoomService roomService;
    //private final PatientRepository patientRepository;
    private final PatientService patientService;

    public AllocationService(AllocationRepository allocationRepository,
                             HistoryRepository historyRepository,
                             UserRepository userRepository,
                             BillService billService,
                             RoomService roomService,
                             PatientRepository patientRepository,
                             PatientService patientService) {
        this.allocationRepository = allocationRepository;
        this.historyRepository = historyRepository;
        this.userRepository = userRepository;
        this.billService = billService;
        this.roomService = roomService;
        this.patientService = patientService;
    }


    public ResponseEntity<Object> createAllocation(
            AllocationDto allocationDto
    ) {
        try {
            Allocation allocation = new Allocation();
            boolean patientExists = patientService.checkPatientExistence(
                    allocationDto.getNationalId()
            );
            if (patientExists) {
                NewPatientDto newPatientDto = new NewPatientDto();
                newPatientDto.setFirstName(allocationDto.getFirstName());
                newPatientDto.setLastName(allocationDto.getLastName());
                newPatientDto.setNationalId(allocation.getNationalId());
                return new ResponseEntity<>("" +
                        "ALLOCATED SUCCESSFULLY", HttpStatus.OK);
            } else {

            }
        } catch (UserAlreadyExistsException existsException) {
            throw new UserAlreadyExistsException();
        }

        return new ResponseEntity<>("Su",HttpStatus.OK);
    }
}
