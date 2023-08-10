package com.assignment.group7.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuccessPropsResponse {
    private final boolean success = true;
    private String message;
    private Object body;
    private Object statusCode;
}
