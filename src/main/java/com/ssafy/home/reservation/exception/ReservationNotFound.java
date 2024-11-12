package com.ssafy.home.reservation.exception;

public class ReservationNotFound extends RuntimeException {
    public ReservationNotFound(Long rId) {
        super(rId + "번 예약을 찾을 수 없습니다.");
    }

    public ReservationNotFound() {
        super("예약을 찾을 수 없습니다.");
    }
}
