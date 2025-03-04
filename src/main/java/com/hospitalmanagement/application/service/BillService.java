package com.hospitalmanagement.application.service;


import com.hospitalmanagement.application.dto.BillDetailsDto;
import com.hospitalmanagement.application.model.Bill;
import com.hospitalmanagement.application.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {


    private final BillRepository billRepository;

    @Autowired
    public BillService(
            BillRepository billRepository
    ){
        this.billRepository = billRepository;
    }



    @Cacheable(value = "bills")
    public ResponseEntity<List<BillDetailsDto>> getAllBills(){
        List<Bill> bills = billRepository.findAll();

        List<BillDetailsDto> billDetails =
                bills
                        .stream().map(bill -> {
                            BillDetailsDto billDetailsDto = new BillDetailsDto();
                            billDetailsDto.setDate(bill.getDate());
                            billDetailsDto.setAmount(bill.getAmount());
                            billDetailsDto.setFullName(bill.getPatient().getFullName());
                            billDetailsDto.setStatus(bill.getStatus());

                            return billDetailsDto;
                        }).toList();

        return new ResponseEntity<>(billDetails, HttpStatus.OK);
    }
}
