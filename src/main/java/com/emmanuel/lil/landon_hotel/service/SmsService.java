package com.emmanuel.lil.landon_hotel.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class SmsService {

    private final WebClient webClient;
    private final String apiKey;
    private final String apiUrl;

    public SmsService(WebClient.Builder webClientBuilder,
                      @Value("${eazzyconnect.apiKey}") String apiKey,
                      @Value("${eazzyconnect.apiUrl}") String apiUrl) {
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
        this.webClient = webClientBuilder.baseUrl(this.apiUrl + "/api/v1").build();
    }

    public Mono<String> sendSms(String phoneNumber, String message) {
        String fullPhoneNumber = "+256" + phoneNumber;

        return webClient.post()
                .uri("/sms/send")
                .header("Accept", "application/vnd.eazzyconnect.v1")
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .header("apiKey", apiKey)
                .bodyValue(Map.of(
                        "phone_number", fullPhoneNumber,
                        "message", message
                ))
                .retrieve()
                .bodyToMono(String.class);
    }

    public ResponseEntity<String> sendMeSms(String phoneNumber, String message) {
        String url = apiUrl + "/api/v1/sms/send";

        Map<String, String> data = new HashMap<>();
        data.put("phone_number", "+256" + phoneNumber);
        data.put("message", message);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/vnd.eazzyconnect.v1");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("apiKey", apiKey);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(data, headers);
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("SMS sending failed: " + e.getMessage());
        }
    }
}
