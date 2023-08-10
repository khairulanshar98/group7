package com.assignment.group7.dto.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecordNotFoundException extends Exception {
    private String message;
    private Throwable cause;

    public RecordNotFoundException() {
        super();
    }

    public RecordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordNotFoundException(String message) {
        super(message);
    }

    public RecordNotFoundException(Throwable cause) {
        super(cause);
    }
}
