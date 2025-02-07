package com.hospitalmanagement.application.dto;

import lombok.Data;

@Data
public class ResetPasswordDto {

    String nationalId;
    String oldPassword;
    String newPassword;
}
