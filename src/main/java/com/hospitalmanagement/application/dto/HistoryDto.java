package com.hospitalmanagement.application.dto;

import lombok.Data;

import java.util.Date;

@Data
public class HistoryDto {

    Integer historyId;
    Date date;
    User user;
    String action;
}
