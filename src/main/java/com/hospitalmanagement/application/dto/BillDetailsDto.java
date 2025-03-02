package com.hospitalmanagement.application.dto;


import lombok.Data;

import java.util.Date;

@Data
public class BillDetailsDto {

    Integer billId;
    Integer amount;
    String fullName;
    String status;
    Date date;
}
