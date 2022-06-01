package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.exception.InternalValidationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        }

        Map<String, Object> responseDict = Map.of("success", false, "data", errors);
        return ResponseEntity.unprocessableEntity().body(responseDict);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleValidationException(HttpMessageNotReadableException ex) {
        if (ex.getCause() instanceof JsonMappingException) {
            var mappingEx = (JsonMappingException) ex.getCause();
            var fieldName = mappingEx.getPath().get(0).getFieldName();

            Map<String, Object> responseDict = Map.of(
                    "success", false,
                    "data", Map.of(fieldName, "Invalid format"));
            return ResponseEntity.unprocessableEntity().body(responseDict);
        }

        return ResponseEntity.unprocessableEntity().body(Map.of("success", false));
    }

    @ExceptionHandler(InternalValidationException.class)
    public ResponseEntity<Object> handleValidationException(InternalValidationException ex) {
        Map<String, Object> responseDict = Map.of("success", false);
        if (ex.getErrors() != null)
            responseDict.put("data", ex.getErrors());

        return ResponseEntity.unprocessableEntity().body(responseDict);
    }
}

