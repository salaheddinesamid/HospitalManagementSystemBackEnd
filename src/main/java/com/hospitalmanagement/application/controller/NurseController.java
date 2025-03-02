package com.hospitalmanagement.application.controller;

import com.hospitalmanagement.application.dto.NurseDto;
import com.hospitalmanagement.application.model.Nurse;
import com.hospitalmanagement.application.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/nurse")
public class NurseController {

    private NurseService nurseService;

    @Autowired
    public NurseController(NurseService nurseService){
        this.nurseService = nurseService;
    }

    @GetMapping("get_all")
    public List<NurseDto> getAllNurses(){
        return nurseService.allNurse();
    }
}
