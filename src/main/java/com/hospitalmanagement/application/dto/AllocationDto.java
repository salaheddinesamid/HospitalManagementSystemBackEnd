package com.hospitalmanagement.application.dto;

import lombok.Data;

@Data
public class AllocationDto {

    PatientDto patientDto;
    Integer roomId;
}
