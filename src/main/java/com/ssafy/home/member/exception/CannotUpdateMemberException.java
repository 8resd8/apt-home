package com.ssafy.home.member.exception;

public class CannotUpdateMemberException extends RuntimeException {
    public CannotUpdateMemberException(String message) {
        super(message);
    }

    public CannotUpdateMemberException() {
        super("멤버 업데이트에 실패했습니다.");
    }
}
