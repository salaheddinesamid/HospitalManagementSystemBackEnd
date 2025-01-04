package com.hospitalmanagement.application.service;


import com.hospitalmanagement.application.model.Patient;
import com.hospitalmanagement.application.model.Room;
import com.hospitalmanagement.application.repository.RoomRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    public List<Room> getAvailableRooms(){

    }
    public ResponseEntity<Object> findPatientRoom(String firstName){
        if(){

        }else {

        }
    }

    public void
}
