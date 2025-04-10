package com.weatherApp.weatherApp.Exceptions;

public class CoordinatesNotFoundException extends RuntimeException {
    public CoordinatesNotFoundException(String message) {
        super(message);
    }
}
