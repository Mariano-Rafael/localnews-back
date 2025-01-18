package com.localnews.localnews.controllers;


import com.localnews.localnews.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@PropertySource("classpath:/application-secrets.properties")
public class WeatherController {

    private final WeatherService weatherService;

    @Value("${weatherApi.key}")
    String apiKey;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/api/weather")
    public ResponseEntity<?> getWeather(@RequestParam(required = false) String city) {
        String response = weatherService.getWeather(city, apiKey);
        return ResponseEntity.ok(response);
    }
}




