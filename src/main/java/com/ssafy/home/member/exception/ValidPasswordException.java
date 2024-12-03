package com.ssafy.home.member.exception;

public class ValidPasswordException extends RuntimeException {
    public ValidPasswordException(String message) {
        super(message);
    }

    public ValidPasswordException() {
        super("비밀번호가 일치하지 않습니다.");
    }
}
