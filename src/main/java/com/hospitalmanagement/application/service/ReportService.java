package com.hospitalmanagement.application.service;

import com.hospitalmanagement.application.model.Report;
import com.hospitalmanagement.application.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<Report> getAllReports(){
        return reportRepository.findAll();
    }

    /* To be completed...
    public ResponseEntity<Object> newReport(){

    }
     */
}
