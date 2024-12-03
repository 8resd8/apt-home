package com.ssafy.home.member.exception;

public class NotFoundMemberException extends RuntimeException {
    public NotFoundMemberException(String message) {
        super(message);
    }

    public NotFoundMemberException() {
        super("멤버를 찾을 수 없습니다.");
    }
}
