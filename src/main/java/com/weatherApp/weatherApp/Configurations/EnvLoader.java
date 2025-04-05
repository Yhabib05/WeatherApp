package com.weatherApp.weatherApp.Configurations;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvLoader {

    @PostConstruct
    public void loadEnv() {
        io.github.cdimascio.dotenv.Dotenv dotenv = io.github.cdimascio.dotenv.Dotenv.configure().load();
        System.setProperty("coordinates.api.key", dotenv.get("COORDINATES_API_KEY"));
        System.setProperty("weather.api.key", dotenv.get("WEATHER_API_KEY"));
    }
}
