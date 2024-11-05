package com.ssafy.home.auth.exception;

public class DuplicateException extends RuntimeException {
    public DuplicateException() {
        super("아이디가 이미 존재합니다.");
    }
}
