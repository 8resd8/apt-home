package com.ssafy.home.estate.exception;

import com.ssafy.home.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ForbiddenException extends CustomException {
    public ForbiddenException() {
        super(HttpStatus.FORBIDDEN, "권한이 없습니다.");
    }

    public ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
