package com.ssafy.home.review.exception;

import com.ssafy.home.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class DuplicateReviewException extends RuntimeException {

    public DuplicateReviewException() {
        super("이미 리뷰가 존재합니다.");
    }

    public DuplicateReviewException(String message) {
        super(message);
    }
}
