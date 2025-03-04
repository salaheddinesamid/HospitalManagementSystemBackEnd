package com.hospitalmanagement.application.service;


import com.hospitalmanagement.application.dto.PatientDto;
import com.hospitalmanagement.application.dto.RoomDetailsDto;
import com.hospitalmanagement.application.exception.PatientRoomNotFoundException;
import com.hospitalmanagement.application.model.Patient;
import com.hospitalmanagement.application.model.Room;
import com.hospitalmanagement.application.repository.PatientRepository;
import com.hospitalmanagement.application.repository.RoomRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final PatientRepository patientRepository;


    public RoomService(RoomRepository roomRepository, PatientRepository patientRepository) {
        this.roomRepository = roomRepository;
        this.patientRepository = patientRepository;
    }

    @Cacheable(value = "rooms")
    public ResponseEntity<List<RoomDetailsDto>> getAllRooms() {
        List<RoomDetailsDto> roomDetails = roomRepository.findAll().stream()
                .map(room -> new RoomDetailsDto(
                        room.getId(),
                        room.getRoomNumber(),
                        room.getStatus(),
                        room.getCapacity(),
                        room.getPatient() != null ? new PatientDto(
                                room.getPatient().getFirstName(),
                                room.getPatient().getLastName()
                        ) : null
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(roomDetails);
    }

    public ResponseEntity<Object> findPatientRoom(String firstName){
        try{
            Patient patient = patientRepository.findByFirstName(firstName);
            Room room = roomRepository.findByPatient(patient);
            return new ResponseEntity<>("Room number is:" + room.getRoomNumber(), HttpStatus.OK);
        }
        catch (PatientRoomNotFoundException exception){
            throw new PatientRoomNotFoundException();
        }
    }

    public Integer calculatePricePerDay(Integer roomNumber,
                                        Integer totalDays){
        Room room = roomRepository.findByRoomNumber(roomNumber);
        return room.getPricePerDay() * totalDays;
    }
}
