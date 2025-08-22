package com.maike.auth.exception;

public class PasswordWrongException extends RuntimeException {
    public PasswordWrongException(String message) {
        super(message);
    }
}
