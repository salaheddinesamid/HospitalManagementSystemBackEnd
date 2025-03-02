package com.hospitalmanagement.application.controller;


import com.hospitalmanagement.application.dto.BillDetailsDto;
import com.hospitalmanagement.application.model.Bill;
import com.hospitalmanagement.application.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bill")
public class BillController {


    private final BillService billService;

    @Autowired
    public BillController(
            BillService billService
    ){
        this.billService = billService;
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<BillDetailsDto>> getBills(){
        return billService.getAllBills();
    }
}
