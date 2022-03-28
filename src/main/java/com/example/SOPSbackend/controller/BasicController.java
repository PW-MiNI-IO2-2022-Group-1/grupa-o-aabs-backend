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

public class BasicController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            String field = ((FieldError)error).getField();
            String message =  error.getDefaultMessage();
            errors.put(field, message);
        }

        return ResponseEntity.unprocessableEntity()
                .body(new HashMap<String, Object>() {{
                    put("success", false);
                    put("data", errors);
                }});
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleValidationException(HttpMessageNotReadableException ex) {
        if(ex.getCause() instanceof JsonMappingException) {
            var mappingEx = (JsonMappingException)ex.getCause();
            return ResponseEntity.unprocessableEntity()
                    .body(new HashMap<String, Object>() {{
                        put("success", false);
                        put("data", new HashMap<String, Object>() {{
                            put(mappingEx.getPath().get(0).getFieldName(), "Invalid format");
                        }});
                    }});
        }

        return ResponseEntity.unprocessableEntity()
                .body(new HashMap<String, Object>() {{
                    put("success", false);
                }});
    }

    @ExceptionHandler(InternalValidationException.class)
    public ResponseEntity<Object> handleValidationException(InternalValidationException ex) {
        return ResponseEntity.unprocessableEntity()
                .body(new HashMap<String, Object>() {{
                    put("success", false);
                    if(ex.getErrors() != null)  put("data", ex.getErrors());
                }});
    }
}
