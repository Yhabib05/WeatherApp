package com.weatherApp.weatherApp.Exceptions;

public class WeatherDataNotFoundException extends RuntimeException {
    public WeatherDataNotFoundException(String message) {
        super(message);
    }
}
