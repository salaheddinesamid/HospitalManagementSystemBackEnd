package com.hospitalmanagement.application.dto;

import lombok.Data;

import java.util.Date;

@Data

public class AppointmentDto {
    // Personal Information:
    PatientDto patientDto;
    String disease;
    String location;
    Date selectedDate;
    Integer totalPrice;

}
