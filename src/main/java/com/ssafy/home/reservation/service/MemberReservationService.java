package com.ssafy.home.reservation.service;

import com.ssafy.home.reservation.dto.ReservationCreateRequest;
import com.ssafy.home.reservation.dto.ReservationResponse;

import java.util.List;

public interface MemberReservationService {
    void addReservation(ReservationCreateRequest request, String memberId);

    void updateReservation(Long rid, String memberId, ReservationCreateRequest request);

    ReservationResponse getReservation(Long rid);

    List<ReservationResponse> getReservationsByMember(String memberId);
}
