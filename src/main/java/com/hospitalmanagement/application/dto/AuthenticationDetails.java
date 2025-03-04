package com.hospitalmanagement.application.dto;


import lombok.Data;

@Data
public class AuthenticationDetails {

    TokenDTO tokenDTO;
    String firstName;
    String lastName;
    String email;

}
