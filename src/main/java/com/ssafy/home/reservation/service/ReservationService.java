package com.ssafy.home.reservation.service;

import com.ssafy.home.reservation.domain.Reservation;
import com.ssafy.home.reservation.dto.ReservationAddRequest;

import java.util.List;

public interface ReservationService {
    void addReservation(ReservationAddRequest request);

    void updateReservation(Long rid, ReservationAddRequest request);

    void deleteReservation(Long rid);

    Reservation getReservation(Long rid);

    List<Reservation> getReservationsByMember(String memberId);
}
