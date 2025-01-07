package com.hospitalmanagement.application.controller;

import com.hospitalmanagement.application.dto.Emergency;
import com.hospitalmanagement.application.dto.EmergencyDto;
import com.hospitalmanagement.application.service.EmergencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/emergency")
public class EmergencyController {

    private final EmergencyService emergencyService;

    public EmergencyController(EmergencyService emergencyService) {
        this.emergencyService = emergencyService;
    }

    @PostMapping("/new")
    public ResponseEntity<Object> newEmergency(
            @RequestBody EmergencyDto emergencyDto
            ){
        return emergencyService.reportEmergency(emergencyDto);
    }
    @GetMapping("/getAllEmergency/{date}")
    public List<Emergency> getAllEmergency(
            @PathVariable Date date
            ){
        return emergencyService.getDailyEmergency(date);

    }

    @DeleteMapping("/delete_emereggncy")
    public ResponseEntity<Object> deleteEmergency(){
        return emergencyService.
    }
}
