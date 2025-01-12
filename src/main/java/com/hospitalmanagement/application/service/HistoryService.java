package com.hospitalmanagement.application.service;

import com.hospitalmanagement.application.dto.HistoryDto;
import com.hospitalmanagement.application.model.History;
import com.hospitalmanagement.application.repository.HistoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    private final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public ResponseEntity<List<HistoryDto>> getHistory(){
        List<History> histories = historyRepository.findAll();
        List<HistoryDto> historyDtoList = histories
                .stream()
                .map(history -> {
                    HistoryDto historyDto  = new HistoryDto();
                    historyDto.setAction(history.getAction());
                    historyDto.setDate(history.getDate());
                    historyDto.setUser(history.getUser());
                    return historyDto;
                }).toList();

        return new ResponseEntity<>(historyDtoList, HttpStatus.OK);
    }
}
