package com.ssafy.home.review.exception;

public class NotCompleteReviewRxception extends RuntimeException {
    public NotCompleteReviewRxception(String message) {
        super(message);
    }

    public NotCompleteReviewRxception() {
        super("완료된 예약만 리뷰를 남길 수 있습니다.");
    }
}
