package com.example.SOPSbackend.exception;

public class AlreadyReservedException extends Throwable {
    public AlreadyReservedException(String message) {
        super(message);
    }
}
