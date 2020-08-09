package com.isroy.dev.exception;

public class InvalidTokenException extends Exception {
    public InvalidTokenException(String token) {
        super("The token : " + token + " is not recognised by the system");
    }
}
