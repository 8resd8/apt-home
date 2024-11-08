package com.ssafy.home.reservation.service;

import com.ssafy.home.reservation.domain.Reservation;
import com.ssafy.home.reservation.dto.ReservationCreateRequest;

import java.util.List;

public interface ReservationService {
    void updateReservation(Long rid, ReservationCreateRequest request, String memberId);

    void deleteReservation(Long rid);

    Reservation getReservation(Long rid);

    List<Reservation> getAllReservationByMember(String memberId);

    void createReservation(ReservationCreateRequest request, String memberId);


}
