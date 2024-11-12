package com.ssafy.home.reservation.exception;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(Long rId) {
        super(rId + "번 예약을 찾을 수 없습니다.");
    }

    public ReservationNotFoundException() {
        super("예약을 찾을 수 없습니다.");
    }
}
