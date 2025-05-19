package org.example.exceptions;

public class NoManagersAvailableException extends RuntimeException {
    public NoManagersAvailableException(String message) {
        super(message);
    }
}
