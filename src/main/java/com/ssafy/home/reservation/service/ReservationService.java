package com.ssafy.home.reservation.service;

import com.ssafy.home.reservation.domain.Reservation;
import com.ssafy.home.reservation.dto.ReservationCreateRequest;

import java.util.List;

public interface ReservationService {
    void addReservation(ReservationCreateRequest request, String memberId);

    void updateReservation(Long rid, String memberId, ReservationCreateRequest request);

    Reservation getReservation(Long rid);

    List<Reservation> getReservationsByMember(String memberId);
}
