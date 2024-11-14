package com.ssafy.home.auth.exception;

public class EmailVerifyFailException extends RuntimeException {
    public EmailVerifyFailException(String message) {
        super(message);
    }

    public EmailVerifyFailException() {
        super("인증 코드가 일치하지 않습니다.");
    }
}
