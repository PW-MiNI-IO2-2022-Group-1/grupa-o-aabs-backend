package com.example.SOPSbackend.exception;

public class UserAlreadyExistException extends Throwable {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
