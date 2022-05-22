package com.example.SOPSbackend.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class SuccessTrueResponse extends ResponseEntity<Object> {
    public SuccessTrueResponse() {
        super(Map.of("success", true), HttpStatus.OK);
    }
}
