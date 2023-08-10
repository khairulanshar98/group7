package com.assignment.group7.dto.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecordNotCreatedException extends Exception {
    private String message;
    private Throwable cause;

    public RecordNotCreatedException() {
        super();
    }

    public RecordNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordNotCreatedException(String message) {
        super(message);
    }

    public RecordNotCreatedException(Throwable cause) {
        super(cause);
    }
}
