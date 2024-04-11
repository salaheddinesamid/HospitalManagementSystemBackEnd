package com.hospitalmanagement.application.controllers;

import com.hospitalmanagement.application.models.Disease;
import com.hospitalmanagement.application.repositories.DiseaseRepository;
import com.hospitalmanagement.application.services.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("disease")
public class DiseaseController {
    @Autowired
    DiseaseRepository diseaseRepository;
    private final DiseaseService diseaseService;
    public DiseaseController(DiseaseService diseaseService){
        this.diseaseService = diseaseService;
    }
    @GetMapping("/")
    List<Disease> getAllDiseases(){
        return diseaseRepository.findAll();
    }
    @PostMapping("/add")
    ResponseEntity<Object> newDisease(@RequestBody Disease disease){
       return  diseaseService.addNewDisease(disease);
    }
}
