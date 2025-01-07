package com.hospitalmanagement.application.controller;

import com.hospitalmanagement.application.dto.EmergencyDto;
import com.hospitalmanagement.application.service.EmergencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
