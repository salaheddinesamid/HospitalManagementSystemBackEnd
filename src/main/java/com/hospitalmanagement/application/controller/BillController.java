package com.hospitalmanagement.application.controller;

import com.hospitalmanagement.application.model.Bill;
import com.hospitalmanagement.application.service.BillService;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bill")
public class BillController {
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping("/pay_bill")
    public ResponseEntity<Object> payBill(
            Integer billId
    ){
        return billService.payBill(billId);
    }

    @GetMapping("/get_all_bills")
    public List<Bill> getAllBills(){
        return billService.getAllBills();
    }
}

