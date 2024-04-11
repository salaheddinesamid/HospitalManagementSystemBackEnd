package com.hospitalmanagement.application.services;

import com.hospitalmanagement.application.models.Disease;
import com.hospitalmanagement.application.repositories.DiseaseRepository;
import com.hospitalmanagement.application.requesthandler.DiseaseRequest;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class DiseaseService {
    @Autowired
    DiseaseRepository diseaseRepository;
    public ResponseEntity<Object> addNewDisease(Disease disease){
        diseaseRepository.save(disease);
        return new ResponseEntity<>("Disease has been added", HttpStatus.OK);
    }
}
