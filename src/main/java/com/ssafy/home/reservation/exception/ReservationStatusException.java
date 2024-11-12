package com.ssafy.home.reservation.exception;

public class ReservationStatusException extends RuntimeException {
  public ReservationStatusException(String status) {
    super("예약 상태를 " + status + "로 변경하지 못했습니다.");
  }

  public ReservationStatusException() {
    super("예약 상태를 변경하지 못했습니다.");
  }
}
