package org.example.exceptions;

public class HouseManagerAlreadyHiredException extends RuntimeException {
  public HouseManagerAlreadyHiredException(String message) {
    super(message);
  }
}
