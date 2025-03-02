package com.hospitalmanagement.application.dto;


import lombok.Data;

@Data
public class NewPatientDto {
    String firstName;
    String lastName;
    String nationalId;
    String address;
    String phone;
    String email;
    String password;
}
