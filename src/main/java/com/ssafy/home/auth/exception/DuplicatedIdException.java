package com.ssafy.home.auth.exception;

public class DuplicatedIdException extends RuntimeException {
    public DuplicatedIdException() {
        super("이미 사용중인 이메일입니다.");
    }
}
