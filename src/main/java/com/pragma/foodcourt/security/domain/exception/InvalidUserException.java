package com.pragma.foodcourt.security.domain.exception;

public class InvalidUserException extends RuntimeException{

    public InvalidUserException(String message) {
        super(message);
    }

    public InvalidUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
