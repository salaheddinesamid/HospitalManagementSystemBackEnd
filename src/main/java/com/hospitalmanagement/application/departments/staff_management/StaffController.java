package com.hospitalmanagement.application.departments.staff_management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("staff-management")
@CrossOrigin
public class StaffController {
    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("get_staff")
    public List<Staff> getAllStaff(){
        return staffService.getAllStaff();
    }

    @PostMapping("new_staff")
    public ResponseEntity<Object> addStaff(@RequestBody Staff staff){
        return staffService.newStaff(staff);
    }

    @PutMapping("edit/{id}")
    public void editStaff(@PathVariable Integer id, @RequestBody StaffDto staffDto){
        staffService.editStaff(id,staffDto);
    }

}
