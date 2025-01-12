package com.hospitalmanagement.application.controller;

import com.hospitalmanagement.application.dto.HistoryDto;
import com.hospitalmanagement.application.service.HistoryService;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }


    @GetMapping("/")
    public ResponseEntity<List<HistoryDto>> getAllHistory(){
        return historyService.getHistory();
    }
}
