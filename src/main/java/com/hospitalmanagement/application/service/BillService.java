package com.hospitalmanagement.application.service;


import com.hospitalmanagement.application.dto.CreditCardDto;
import com.hospitalmanagement.application.model.Bill;
import com.hospitalmanagement.application.repository.BillRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public List<Bill> getAllBills(){
        return billRepository.findAll();
    }

    public List<Bill> getNotPaid(){
        return billRepository.findAllByStatus("Not paid");
    }

    public List<Bill> getPatientBill(Integer id){
        return billRepository.findAllByPatient_Id(id);
    }


}
