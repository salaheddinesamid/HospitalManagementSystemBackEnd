package com.hospitalmanagement.application.service;

import com.hospitalmanagement.application.dto.Emergency;
import com.hospitalmanagement.application.dto.EmergencyDto;
import com.hospitalmanagement.application.repository.EmergencyRepository;
import com.hospitalmanagement.application.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmergencyService {

    private final EmergencyRepository emergencyRepository;
    private final UserRepository userRepository;

    public EmergencyService(EmergencyRepository emergencyRepository, UserRepository userRepository) {
        this.emergencyRepository = emergencyRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Object> reportEmergency(EmergencyDto emergencyDto){
        User user   = userRepository.findById(emergencyDto.getUserId()).get();
        Emergency emergency = new Emergency();
        emergency.setDate(emergencyDto.getDate());
        emergency.setLocation(emergencyDto.getLocation());
        emergency.setUser(user);
        emergencyRepository.save(emergency);
        return new ResponseEntity<>("EMERGENCY ADDED IN THE SYSTEM", HttpStatus.OK);
    }

    public List<Emergency> getDailyEmergency(Date date){
        return emergencyRepository.findAllByDate(date);
    }

    public ResponseEntity<Object> deleteEmergency(Integer emerghecncyId) {
        emergencyRepository.deleteById(emerghecncyId);
        return new ResponseEntity<>("Emergecny deleted",HttpStatus.OK);
    }
}
