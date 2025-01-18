package com.localnews.localnews.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;

    @Autowired
    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getWeather(String city, String apiKey) {
        String url = "https://api.hgbrasil.com/weather?key=" + apiKey;
        if (city != null && !city.isEmpty()) {
            url += "&city_name=" + city;
        }

        return restTemplate.getForObject(url, String.class);
    }
}
