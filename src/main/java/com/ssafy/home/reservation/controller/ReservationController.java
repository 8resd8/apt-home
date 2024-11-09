package com.ssafy.home.reservation.controller;

import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.global.annotation.Login;
import com.ssafy.home.reservation.domain.Reservation;
import com.ssafy.home.reservation.dto.ReservationCreateRequest;
import com.ssafy.home.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addReservation(@Login Member member, @Validated @RequestBody ReservationCreateRequest request) {
        reservationService.addReservation(request, member.getMid());
    }

    @PutMapping("/{rid}")
    @ResponseStatus(HttpStatus.OK)
    public void updateReservation(@PathVariable Long rid, @Login Member member, @Validated @RequestBody ReservationCreateRequest request) {
        reservationService.updateReservation(rid, member.getMid(), request);
    }

    @GetMapping("/{rid}")
    public ResponseEntity<Reservation> getReservation(@PathVariable Long rid) {
        Reservation reservation = reservationService.getReservation(rid);
        return reservation != null ? ResponseEntity.ok(reservation) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getReservations(@Login Member member) {
        List<Reservation> reservations = reservationService.getReservationsByMember(member.getMid());
        return ResponseEntity.ok(reservations);
    }
}
