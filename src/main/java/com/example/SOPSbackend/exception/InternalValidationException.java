package com.example.SOPSbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * <p>This exception is thrown when our own <i>internal</i> validation fails.</p>
 * <p> Beyond our own validation, some preliminary validation
 * is executed by Spring Boot when it maps objects from requests
 * to DTOs. Exceptions with similar
 * name might be thrown then.</p>
 *
 * This exception, however, is specifically for our own validation.
 * Thus, the class name <i>internal</i>.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InternalValidationException extends RuntimeException {
    private Map<String, String> errors;
}
