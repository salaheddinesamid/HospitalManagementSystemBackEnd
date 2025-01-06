package com.hospitalmanagement.application.dto;

import lombok.Data;

@Data
public class UserRegistrationDTO {

    String firstName;
    String lastName;
    String email;
    String nationalId;
    String homeAddress;
    String city;
    String password;
}
