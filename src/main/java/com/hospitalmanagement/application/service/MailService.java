package com.hospitalmanagement.application.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailService {


    private MailSender mailSender;
    private SimpleMailMessage simpleMailMessage;

    /* To be implemented...
    public void sendMessage(Object message){
        SimpleMailMessage msg = new SimpleMailMessage(message);
    }

     */
}
