package com.hospitalmanagement.application.requesthandler;

public class PutRequestAppointment {
    String newDate;
    String newDoctor;
    public PutRequestAppointment(String newDate, String newDoctor){
        this.newDate = newDate;
        this.newDoctor = newDoctor;
    }

    public String getNewDate() {
        return newDate;
    }

    public void setNewDate(String newDate) {
        this.newDate = newDate;
    }

    public String getNewDoctor() {
        return newDoctor;
    }

    public void setNewDoctor(String newDoctor) {
        this.newDoctor = newDoctor;
    }
}
