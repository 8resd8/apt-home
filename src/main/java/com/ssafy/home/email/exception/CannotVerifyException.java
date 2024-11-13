package com.ssafy.home.email.exception;

public class CannotVerifyException extends RuntimeException {
    public CannotVerifyException(String message) {
        super(message);
    }

    public CannotVerifyException() {
        super("인증 코드가 일치하지 않습니다.");
    }
}
