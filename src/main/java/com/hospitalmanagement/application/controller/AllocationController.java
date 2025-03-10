package com.hospitalmanagement.application.controller;


import com.hospitalmanagement.application.dto.AllocationDto;
import com.hospitalmanagement.application.dto.AllocationResponseDto;
import com.hospitalmanagement.application.model.Allocation;
import com.hospitalmanagement.application.service.AllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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

    @GetMapping("/get_all")
    public ResponseEntity<List<AllocationResponseDto>> getAllocations(){
        return allocationService.getAllocations();
    }

    @PostMapping("/new")
    public CompletableFuture<ResponseEntity<?>> newAllocation(@RequestBody AllocationDto allocationDto){
        return allocationService.allocate(allocationDto);
    }
}
