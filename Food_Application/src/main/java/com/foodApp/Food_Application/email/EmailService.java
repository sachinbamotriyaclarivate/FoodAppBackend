package com.foodApp.Food_Application.email;

import org.springframework.stereotype.Service;

@Service

public interface EmailService {
    // To send a simple email
    String sendSimpleMail(EmailDetails details);
 
    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details);
}