package com.hospitalmanagement.application.dto;


import lombok.Data;

@Data
public class PatientDto {
    String fullName;
    String nationalId;
    String address;
    String phone;
    String email;
}
