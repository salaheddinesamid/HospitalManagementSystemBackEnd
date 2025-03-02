package com.hospitalmanagement.application.dto;

import lombok.Data;

@Data
public class NurseDto {

    String fullName;
    String email;
    String nationalId;
    Integer badgeNumber;
    String gender;
}
