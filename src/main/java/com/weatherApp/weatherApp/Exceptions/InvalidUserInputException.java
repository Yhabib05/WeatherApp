package com.weatherApp.weatherApp.Exceptions;

public class InvalidUserInputException extends RuntimeException{
    public InvalidUserInputException(String message){
        super(message);
    }
}
