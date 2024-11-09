package com.ssafy.home.reservation.exception;

public class NotFoundReservation extends RuntimeException {
    public NotFoundReservation(Long rId) {
        super(rId + "님의 예약을 찾을 수 없습니다.");
    }

    public NotFoundReservation() {
        super("예약을 찾을 수 없습니다.");
    }
}
