package com.ssafy.home.reservation.exception;

public class NotFoundReservationException extends RuntimeException {
    public NotFoundReservationException(Long rId) {
        super(rId + "번 예약을 찾을 수 없습니다.");
    }

    public NotFoundReservationException() {
        super("예약을 찾을 수 없습니다.");
    }

    public NotFoundReservationException(String message) {
        super(message);
    }
}
