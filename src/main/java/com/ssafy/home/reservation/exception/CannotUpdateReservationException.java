package com.ssafy.home.reservation.exception;

public class CannotUpdateReservationException extends RuntimeException {

    public CannotUpdateReservationException() {
        super("예약 수정 실패했습니다.");
    }

    public CannotUpdateReservationException(String message) {
        super(message);
    }
}
