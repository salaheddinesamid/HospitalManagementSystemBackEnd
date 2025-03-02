package com.hospitalmanagement.application.controller;


import com.hospitalmanagement.application.dto.AppointmentDto;
import com.hospitalmanagement.application.model.Appointment;
import com.hospitalmanagement.application.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<Appointment>> getAllAppointments(){
        return appointmentService.getAllAppointment();
    }

    @PostMapping("/new")
    public ResponseEntity<?> newAppointment(@RequestBody AppointmentDto appointmentDto){
        return appointmentService.createAppointment(appointmentDto);
    }

    @DeleteMapping("delete/{appointmentId}")
    public ResponseEntity<Object> cancelAppointment(@PathVariable Integer appointmentId){
        return appointmentService.deleteAppointment(appointmentId);
    }
}
