package com.hospitalmanagement.application.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DoctorDto {
    String fullName;
    Integer age;
    String nationalId;
    String professionalId;
    Integer yearsOfExperience;
    String specialization;
    Date birthday;
    String address;
}
