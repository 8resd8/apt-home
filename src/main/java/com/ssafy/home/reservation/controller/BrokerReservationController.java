package com.ssafy.home.reservation.controller;

import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.global.annotation.Login;
import com.ssafy.home.reservation.service.BrokerReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/broker/reservation")
public class BrokerReservationController {

    private final BrokerReservationService brokerReservationService;


    @PatchMapping("/{reservationId}/reserve")
    @ResponseStatus(HttpStatus.OK)
    public void reserveReservation(@PathVariable Long reservationId, @Login Broker broker, @RequestParam String brokerMemo) {
        brokerReservationService.reserveReservation(reservationId, broker, brokerMemo);
    }

    @PatchMapping("/{reservationId}/complete")
    @ResponseStatus(HttpStatus.OK)
    public void completeReservation(@PathVariable Long reservationId, @Login Broker broker) {
        brokerReservationService.completeReservation(reservationId, broker);
    }

    @PatchMapping("/{reservationId}/cancel")
    @ResponseStatus(HttpStatus.OK)
    public void cancelReservation(@PathVariable Long reservationId, @Login Broker broker) {
        brokerReservationService.cancelReservation(reservationId, broker);
    }
}
