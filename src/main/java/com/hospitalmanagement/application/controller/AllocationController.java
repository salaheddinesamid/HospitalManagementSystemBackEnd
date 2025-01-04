package com.hospitalmanagement.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/allocation")
public class AllocationController {

    @PostMapping("/new")
    public ResponseEntity<Object> createNewAllocation(){

    }
}
