package com.hospitalmanagement.application.dto;

import com.hospitalmanagement.application.model.Patient;
import lombok.Data;

@Data
public class AllocationDto {

    PatientDto patientDto;
    Integer roomId;
}
