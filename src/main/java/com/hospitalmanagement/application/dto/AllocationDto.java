package com.hospitalmanagement.application.dto;

import com.hospitalmanagement.application.model.Patient;
import lombok.Data;

@Data
public class AllocationDto {

    Integer roomNumber;
    String name;
}
