package com.example.SOPSbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InternalValidationException extends RuntimeException {
    private Map<String, String> errors;
}
