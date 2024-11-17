package com.ssafy.home.reservation.exception;

public class UpdateStatusReservationException extends RuntimeException {
  public UpdateStatusReservationException(String status) {
    super("예약 상태를 " + status + "로 변경하지 못했습니다.");
  }

  public UpdateStatusReservationException() {
    super("예약 상태를 변경하지 못했습니다.");
  }
}
