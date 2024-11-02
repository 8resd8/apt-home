package com.ssafy.home.auth.exception;


public class LoginFailedException extends RuntimeException {
    public LoginFailedException() {
        super("아이디 또는 비밀번호를 확인해 주세요.");
    }
}
