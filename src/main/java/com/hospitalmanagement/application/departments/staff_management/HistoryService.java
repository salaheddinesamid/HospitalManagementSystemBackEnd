package com.hospitalmanagement.application.departments.staff_management;// src/main/java/com/example/lifecare/service/HistoryService.java


import com.hospitalmanagement.application.departments.staff_management.History;
import com.hospitalmanagement.application.departments.staff_management.HistoryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    public List<History> getAllHistory() {
        return historyRepository.findAll();
    }

    public void addHistory(String description) {
        History history = new History(LocalDateTime.now(), description);
        historyRepository.save(history);
    }
}
