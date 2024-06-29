package com.hospitalmanagement.application.departments.staff_management;

import jakarta.persistence.Entity;


public class StaffDto {
    String fullName;
    String role;
    String status;
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public StaffDto(String fullName, String role, String status){
        this.fullName = fullName;
        this.role = role;
        this.status = status;

    }
}
