package org.example.exceptions;

public class HouseManagerNotInCompanyException extends RuntimeException {
    public HouseManagerNotInCompanyException(String message) {
        super(message);
    }
}
