package com.hospitalmanagement.application.departments.billing;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;
    public InvoiceController(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }
    @GetMapping("")
    List<Invoice> getAllInvoices(){
        return invoiceService.getInvoices();
    }
    @PutMapping("pay/{id}")
    public ResponseEntity<Object> pay(@PathVariable Long id){
        return invoiceService.payInvoice(id);
    }
}
