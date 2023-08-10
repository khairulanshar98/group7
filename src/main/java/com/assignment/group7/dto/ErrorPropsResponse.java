package com.assignment.group7.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorPropsResponse {
    private final boolean success = false;
    private String message;
    private Object cause;
    private Object statusCode;
}
