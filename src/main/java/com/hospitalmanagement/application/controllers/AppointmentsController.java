package com.hospitalmanagement.application.controllers;

import com.hospitalmanagement.application.models.Appointment;
import com.hospitalmanagement.application.requesthandler.PutRequestAppointment;
import com.hospitalmanagement.application.services.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("appointments")
public class AppointmentsController {
    private final AppointmentService appointmentService;
    public AppointmentsController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }
    @GetMapping("/")
    public List<Appointment> appointments(){
        return appointmentService.getAllAppointments();
    }
    @PostMapping("/new")
    public ResponseEntity<Object> newAppointment(@RequestBody Appointment appointment){
        return appointmentService.addNewAppointment(appointment);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Object> editAppointment(@PathVariable Long id, @RequestBody PutRequestAppointment putRequestAppointment){
        return appointmentService.updateAppointment(id,putRequestAppointment.getNewDate(),putRequestAppointment.getNewDoctor());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteAppointment(@PathVariable Long id){
        return appointmentService.deleteAppointment(id);
    }

}
