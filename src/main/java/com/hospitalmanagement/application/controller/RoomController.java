package com.hospitalmanagement.application.controller;


import com.hospitalmanagement.application.dto.RoomDetailsDto;
import com.hospitalmanagement.application.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(
            RoomService roomService
    ){
        this.roomService = roomService;
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<RoomDetailsDto   >> allRooms(){
        return roomService.getAllRooms();
    }

}
