package com.hospitalmanagement.application.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EmergencyDto {
    Integer userId;
    Date date;
    String location;

}
