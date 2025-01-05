package com.hospitalmanagement.application.service;


import com.hospitalmanagement.application.exception.PatientRoomNotFoundException;
import com.hospitalmanagement.application.model.Patient;
import com.hospitalmanagement.application.model.Room;
import com.hospitalmanagement.application.repository.PatientRepository;
import com.hospitalmanagement.application.repository.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final PatientRepository patientRepository;

    public RoomService(RoomRepository roomRepository, PatientRepository patientRepository) {
        this.roomRepository = roomRepository;
        this.patientRepository = patientRepository;
    }

    public List<Room> getAllRooms(){
        return roomRepository.findAll();
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
}
