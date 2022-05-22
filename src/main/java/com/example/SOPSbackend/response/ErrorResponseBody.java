package com.example.SOPSbackend.response;

import lombok.Getter;

@Getter
public class ErrorResponseBody {
    private final boolean success = false;
    private final String msg = "No such doctor";
}
