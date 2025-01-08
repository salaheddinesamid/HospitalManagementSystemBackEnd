package com.hospitalmanagement.application.service;


import com.hospitalmanagement.application.model.Bill;
import com.hospitalmanagement.application.model.Report;
import com.hospitalmanagement.application.model.User;
import com.hospitalmanagement.application.repository.BillRepository;
import com.hospitalmanagement.application.repository.ReportRepository;
import com.hospitalmanagement.application.repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BillService {

    private final BillRepository billRepository;
    private final UserRepository userRepository;
    private final ReportRepository reportRepository;

    public BillService(BillRepository billRepository, UserRepository userRepository, ReportRepository reportRepository) {
        this.billRepository = billRepository;
        this.userRepository = userRepository;
        this.reportRepository = reportRepository;
    }

    public List<Bill> getAllBills(){
        return billRepository.findAll();
    }

    public ResponseEntity<Object> payBill(Integer billingId){
        Bill bill = billRepository.findById(billingId).get();
        bill.setStatus("Paid");
        billRepository.save(bill);
        return new ResponseEntity<>("Billing paid", HttpStatus.OK);

    }

    public ResponseEntity<Object> reportUnpaidBill(
            String email
    ){
        User user = userRepository.findByEmail(email);
        Report report = new Report();
        report.setDate(new Date());
        report.setSubject("UNPAID BILL");
        report.setUser(user);
        reportRepository.save(report);
        return new ResponseEntity<>("New report created",
                HttpStatus.OK
                );
    }

    public List<Bill> getNotPaid(){
        return billRepository.findAllByStatus("Not paid");
    }

    public List<Bill> getPatientBill(Integer id){
        return billRepository.findAllByPatient_Id(id);
    }
}
