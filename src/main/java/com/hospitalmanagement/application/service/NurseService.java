package com.hospitalmanagement.application.service;

import com.hospitalmanagement.application.dto.NurseDto;
import com.hospitalmanagement.application.model.Nurse;
import com.hospitalmanagement.application.repository.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NurseService {

    private NurseRepository nurseRepository;

    @Autowired
    public NurseService(NurseRepository nurseRepository){
        this.nurseRepository = nurseRepository;
    }


    @Cacheable(value = "nurses")
    public List<NurseDto> allNurse(){
        List<Nurse> nurses = nurseRepository.findAll();

        return nurses
                .stream()
                .map(nurse -> {
                    NurseDto nurseDto = new NurseDto();
                    nurseDto.setFullName(nurse.getFirstName() + " " + nurse.getLastName());
                    nurseDto.setEmail(nurse.getEmail());
                    nurseDto.setBadgeNumber(nurse.getBadgeNumber());
                    return nurseDto;
                }).toList();
    }
}
