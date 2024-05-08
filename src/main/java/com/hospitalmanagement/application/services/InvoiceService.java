package com.hospitalmanagement.application.services;

import com.hospitalmanagement.application.models.Appointment;
import com.hospitalmanagement.application.models.Invoice;
import com.hospitalmanagement.application.repositories.InvoiceRepository;
import com.hospitalmanagement.application.repositories.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    PricesRepository pricesRepository;
    public void newInvoice(Appointment appointment){
        Invoice invoice = new Invoice(appointment.getPatient(),200,"Not paid");
        invoiceRepository.save(invoice);
        new ResponseEntity<>("Invoice added", HttpStatus.OK);
    }
    public ResponseEntity<Object> payInvoice(Long id){
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        invoice.get().setStatus("Paid");
        invoiceRepository.save(invoice.get());
        return new ResponseEntity<>("Your invoice has been paid.",HttpStatus.OK);
    }
    public List<Invoice> getInvoices(){
        List<Invoice> filteredInvoices = new ArrayList<>();
        for(Invoice invoice : invoiceRepository.findAll()){
            if(invoice.getAmount() != 0){
                filteredInvoices.add(invoice);
            }
        }
        return filteredInvoices;
    }
}
