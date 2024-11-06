package com.ssafy.home.auth.exception;

public class DeleteAccountFailedException extends RuntimeException {
    public DeleteAccountFailedException(String message) {
        super(message);
    }
}
