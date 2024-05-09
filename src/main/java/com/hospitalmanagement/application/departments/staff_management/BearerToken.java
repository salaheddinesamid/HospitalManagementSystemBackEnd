package com.hospitalmanagement.application.departments.staff_management;

import lombok.Data;

@Data
public class BearerToken {
    private String accessToken ;
    private String tokenType ;
    public BearerToken(String accessToken , String tokenType) { this.tokenType = tokenType ;
        this.accessToken = accessToken;
    }
}
