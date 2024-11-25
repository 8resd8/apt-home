package com.ssafy.home.reservation.service;

import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.reservation.dto.ReservationResponse;

import java.util.List;

public interface BrokerReservationService {
    void reserveReservation(Long reservationId, Broker broker, String brokerMemo);

    void completeReservation(Long reservationId, Broker broker);

    void cancelReservation(Long reservationId, Broker broker);

    List<ReservationResponse> getReservationsByBroker(String bid);
}
