package com.example.SOPSbackend.exception;

public class InvalidVaccinationStateException extends Throwable{
    public InvalidVaccinationStateException(String message) {
        super(message);
    }
}
