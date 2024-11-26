package com.ssafy.home.reservation.controller;

import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.global.annotation.Login;
import com.ssafy.home.reservation.dto.ReservationCreateRequest;
import com.ssafy.home.reservation.dto.ReservationMemberResponse;
import com.ssafy.home.reservation.dto.ReservationResponse;
import com.ssafy.home.reservation.service.MemberReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/reservation")
public class MemberReservationController {

    private final MemberReservationService memberReservationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addReservation(@Login Member member, @Validated @RequestBody ReservationCreateRequest request) {
        memberReservationService.addReservation(request, member.getMid());
    }

    @PutMapping("/{rid}")
    @ResponseStatus(HttpStatus.OK)
    public void updateReservation(@PathVariable Long rid, @Login Member member, @Validated @RequestBody ReservationCreateRequest request) {
        memberReservationService.updateReservation(rid, member.getMid(), request);
    }

    @GetMapping("/{rid}")
    public ResponseEntity<ReservationResponse> getReservation(@PathVariable Long rid) {
        ReservationResponse reservation = memberReservationService.getReservation(rid);
        return ResponseEntity.ok(reservation);
    }

    @GetMapping
    public ResponseEntity<List<ReservationMemberResponse>> getReservations(@Login Member member) {
        List<ReservationMemberResponse> reservations = memberReservationService.getReservationsByMember(member.getMid());
        return ResponseEntity.ok(reservations);
    }
}
