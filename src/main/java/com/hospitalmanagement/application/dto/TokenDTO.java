package com.hospitalmanagement.application.dto;

import lombok.Data;

@Data
public class TokenDTO {
    String bearer;
    public TokenDTO(String token){
        this.bearer = token;
    }
}
