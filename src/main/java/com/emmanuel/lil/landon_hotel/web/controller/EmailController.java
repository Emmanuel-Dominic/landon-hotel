package com.emmanuel.lil.landon_hotel.web.controller;

import com.emmanuel.lil.landon_hotel.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send-email")
    public String sendEmail(@RequestParam String to) {
        emailService.sendEmail(to, "Test Email", "This is a test email from Spring Boot.");
        return "Email Sent Successfully!";
    }
}