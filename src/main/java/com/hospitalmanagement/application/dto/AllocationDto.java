package com.hospitalmanagement.application.dto;

import com.hospitalmanagement.application.model.Patient;
import lombok.Data;

@Data
public class AllocationDto {

    Integer receptionistId;
    Integer roomNumber;
    String name;
}
