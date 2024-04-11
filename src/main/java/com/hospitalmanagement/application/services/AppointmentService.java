package com.hospitalmanagement.application.services;

import com.hospitalmanagement.application.models.Appointment;
import com.hospitalmanagement.application.models.Invoice;
import com.hospitalmanagement.application.repositories.AppointmentsRepository;
import com.hospitalmanagement.application.repositories.InvoiceRepository;
import com.hospitalmanagement.application.requesthandler.PutRequestAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    AppointmentsRepository appointmentsRepository;
    private  final InvoiceService invoiceService;
    public AppointmentService(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }
    public List<Appointment> getAllAppointments(){
        return appointmentsRepository.findAll();
    }
    public ResponseEntity<Object> addNewAppointment(Appointment appointment){
        appointmentsRepository.save(appointment);
        invoiceService.newInvoice(appointment);
        return new ResponseEntity<>("Your appointment has been added successfully", HttpStatus.OK);
    }
    public ResponseEntity<Object> deleteAppointment(Long id){
        appointmentsRepository.deleteById(id);
        return new ResponseEntity<>("Your appointment has been deleted",HttpStatus.OK);
    }
    public ResponseEntity<Object> updateAppointment(Long id,String date, String doctor){
        appointmentsRepository.findById(id).map(
                appointment -> {
                    appointment.setDoctor(doctor);
                    appointment.setDate(date);
                    return appointmentsRepository.save(appointment);
                }
        );
        return new ResponseEntity<>("The appointment has been modified",HttpStatus.OK);
    }
}
