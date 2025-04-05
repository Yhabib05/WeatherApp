package com.weatherApp.weatherApp.Services.Impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weatherApp.weatherApp.Entities.Coord;
import com.weatherApp.weatherApp.Entities.WeatherInformation;
import com.weatherApp.weatherApp.Services.CoordinatesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CoordinatesServiceImpl implements CoordinatesService {
    @Value("${coordinates.api.key}")
    private String coordinatesKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Coord getCoordinates(String city) {
        try{
            String url = "https://api.opencagedata.com/geocode/v1/json?q=" + city + "&key=" + coordinatesKey;

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            JsonNode root = objectMapper.readTree(response.getBody());

            JsonNode firstResult = root.path("results").get(0);

            double lat = firstResult.path("geometry").path("lat").asDouble();
            double lon = firstResult.path("geometry").path("lng").asDouble();

            return new Coord(lat,lon);
        } catch(Exception e){
            throw new RuntimeException("Failed to fetch coordinates for city: " + city, e);
        }

    }
}

