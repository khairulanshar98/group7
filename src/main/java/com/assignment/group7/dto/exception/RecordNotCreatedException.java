package com.assignment.group7.dto.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecordNotCreatedException extends Exception {
    private String message;
    private Throwable cause;
}
