package com.hospitalmanagement.application.departments.staff_management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nurse")
@CrossOrigin
public class NurseController {

    @Autowired
    NurseService nurseService;

    @PostMapping("/new")
    public ResponseEntity<Object> addNewNurse(@RequestBody Nurse nurse){
        return nurseService.addNurse(nurse);
    }
}
