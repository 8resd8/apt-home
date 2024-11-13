package com.ssafy.home.review.exception;

public class CannotUpdateReviewException extends RuntimeException {
    public CannotUpdateReviewException(String message) {
        super(message);
    }

    public CannotUpdateReviewException() {
        super("공인중개사가 이미 답글 작성을 완료해서 더이상 수정할 수 없습니다.");
    }
}
