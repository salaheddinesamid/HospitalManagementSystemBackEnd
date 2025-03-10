package com.hospitalmanagement.application.dto;


import lombok.Data;

@Data
public class PatientDto {
    String firstName;
    String lastName;
    String nationalId;
    String address;
    String phone;
    String email;
    String password;

    public PatientDto(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PatientDto(){

    }

}
