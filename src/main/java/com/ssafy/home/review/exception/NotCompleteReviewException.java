package com.ssafy.home.review.exception;

public class NotCompleteReviewException extends RuntimeException {
    public NotCompleteReviewException(String message) {
        super(message);
    }

    public NotCompleteReviewException() {
        super("완료된 예약만 리뷰를 남길 수 있습니다.");
    }
}
