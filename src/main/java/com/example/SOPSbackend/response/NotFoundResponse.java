package com.example.SOPSbackend.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class NotFoundResponse extends ResponseEntity<Object> {
    public NotFoundResponse() {
        super(new ErrorResponseBody(), HttpStatus.NOT_FOUND);
    }
}
