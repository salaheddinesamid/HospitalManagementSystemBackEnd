package com.hospitalmanagement.application.departments.appointment_management;

import com.hospitalmanagement.application.departments.billing.InvoiceService;
import com.hospitalmanagement.application.departments.staff_management.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    AppointmentsRepository appointmentsRepository;
    private final HistoryService historyService;
    private  final InvoiceService invoiceService;
    public AppointmentService(HistoryService historyService, InvoiceService invoiceService){
        this.historyService = historyService;
        this.invoiceService = invoiceService;
    }
    public List<Appointment> getAllAppointments(){
        return appointmentsRepository.findAll();
    }
    public ResponseEntity<Object> addNewAppointment(Appointment appointment){
        appointmentsRepository.save(appointment);
        invoiceService.newInvoice(appointment);
        historyService.addHistory("Added appointment for patient: " + appointment.getId());
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
