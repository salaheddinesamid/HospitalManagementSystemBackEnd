package com.hospitalmanagement.application.departments.appointment_management;

import com.hospitalmanagement.application.departments.billing.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public long getTotal(){
       return appointmentsRepository.count();
    }
}
