package com.ssafy.home.auth.exception;

public class DuplicatedException extends RuntimeException {
    public DuplicatedException() {
        super("아이디가 이미 존재합니다.");
    }
}
