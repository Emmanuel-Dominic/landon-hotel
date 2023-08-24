package com.emmanuel.lil.landon_hotel.web.controller;

import com.emmanuel.lil.landon_hotel.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class SmsController {

    @Autowired
    private SmsService smsService;

    @GetMapping("/send-sms")
    public Mono<String> sendSms(@RequestParam String phoneNumber, @RequestParam String message) {
        return smsService.sendSms(phoneNumber, message);
    }

    @GetMapping("/send-me-sms")
    public ResponseEntity<String> sendMeSms(@RequestParam String phoneNumber, @RequestParam String message) {
        return smsService.sendMeSms(phoneNumber, message);
    }
}
