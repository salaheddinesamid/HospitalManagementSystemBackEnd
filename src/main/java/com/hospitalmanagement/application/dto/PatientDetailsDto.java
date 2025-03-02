package com.hospitalmanagement.application.dto;


import lombok.Data;

@Data

public class PatientDetailsDto {

    Integer patientId;
    String fullName;
    String address;
    String email;
    String phone;
    String birth;
    String nationalId;

}
