package com.hospitalmanagement.application.dto;


import com.hospitalmanagement.application.model.ContactPatient;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PatientDto {
    String fullName;
    String nationalId;
    String address;
    String phone;
    String email;
}
