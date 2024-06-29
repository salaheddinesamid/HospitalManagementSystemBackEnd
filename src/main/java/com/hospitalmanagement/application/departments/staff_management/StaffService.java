package com.hospitalmanagement.application.departments.staff_management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class StaffService {

    @Autowired
    StaffRepository staffRepository;

    public ArrayList<Staff> getAllStaff(){
        return (ArrayList<Staff>) staffRepository.findAll();
    }

    ResponseEntity<Object> newStaff(Staff staff){
        staffRepository.save(staff);
        return new ResponseEntity<>("Staff Has been added successfully", HttpStatus.OK);
    }

    public ResponseEntity<String> editStaff(Integer id, StaffDto staffDto) {
        Optional<Staff> optionalStaff = staffRepository.findById(id);
        if (optionalStaff.isPresent()) {
            Staff staff = optionalStaff.get();
            staff.setFullName(staffDto.getFullName());
            staff.setStatus(staffDto.getStatus());
            staff.setRole(staffDto.getRole());
            staffRepository.save(staff);
            return ResponseEntity.ok("Staff has been updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Staff not found");
        }
    }
}
