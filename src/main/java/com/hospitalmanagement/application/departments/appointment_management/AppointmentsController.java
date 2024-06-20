package com.hospitalmanagement.application.departments.appointment_management;

import com.hospitalmanagement.application.requesthandler.PutRequestAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("appointments")
public class AppointmentsController {
    @Autowired
    private AppointmentService appointmentService;


    @GetMapping("/")
    public List<Appointment> appointments(){
        return appointmentService.getAllAppointments();
    }
    @PostMapping("/new")
    public ResponseEntity<Object> newAppointment(@RequestBody Appointment appointment){
        return appointmentService.addNewAppointment(appointment);
    }
    @PutMapping("/edit/{id}")

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteAppointment(@PathVariable Long id){
        return appointmentService.deleteAppointment(id);
    }
    @GetMapping("/total")
    public long total(){
      return appointmentService.getTotal();
    }

}
