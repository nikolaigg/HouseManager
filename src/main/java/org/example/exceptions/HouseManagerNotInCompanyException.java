package org.example.exceptions;

public class HouseManagerNotInCompany extends RuntimeException {
    public HouseManagerNotInCompany(String message) {
        super(message);
    }
}
