package com.ssafy.home.reservation.service;

import com.ssafy.home.auth.domain.Broker;

public interface BrokerReservationService {
    void reserveReservation(Long reservationId, Broker broker, String brokerMemo);

    void completeReservation(Long reservationId, Broker broker);
}
