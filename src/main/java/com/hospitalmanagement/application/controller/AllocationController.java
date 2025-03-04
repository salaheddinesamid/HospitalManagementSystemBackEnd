package com.hospitalmanagement.application.controller;


import com.hospitalmanagement.application.dto.AllocationDto;
import com.hospitalmanagement.application.service.AllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/allocation")
public class AllocationController {

    private final AllocationService allocationService;

    @Autowired
    public AllocationController(
            AllocationService allocationService
    ){
        this.allocationService = allocationService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> newAllocation(@RequestBody AllocationDto allocationDto){
        return allocationService.allocate(allocationDto);
    }
}
