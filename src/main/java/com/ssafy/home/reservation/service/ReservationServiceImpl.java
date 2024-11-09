package com.ssafy.home.reservation.service;

import com.ssafy.home.auth.repository.BrokerMapper;
import com.ssafy.home.global.enums.ReservationStatus;
import com.ssafy.home.reservation.domain.Reservation;
import com.ssafy.home.reservation.dto.ReservationCreateRequest;
import com.ssafy.home.reservation.exception.NotFoundReservation;
import com.ssafy.home.reservation.repository.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final ReservationMapper reservationMapper;

    @Override
    public void addReservation(ReservationCreateRequest request, String memberId) {
        // 1. 예약 생성
        Reservation reservation = Reservation.builder()
                .memberId(memberId)
                .brokerId(request.brokerId())
                .startTime(request.startTime())
                .endTime(request.endTime())
                .clientMemo(request.clientMemo())
                .brokerMemo(request.broker_memo())
                .status(ReservationStatus.CREATED.getValue())
                .build();

        reservationMapper.insertReservation(reservation);

        // 2. 매물 예약 연결
        Long reservationId = reservation.getRid();
        request.estateIds().forEach(estateId ->
                reservationMapper.insertReservationEstate(reservationId, estateId, request.clientMemo())
        );
    }

    @Override
    public void updateReservation(Long rid, String memberId, ReservationCreateRequest request) {
        Reservation reservation = Reservation.builder()
                .rid(rid)
                .memberId(memberId)
                .brokerId(request.brokerId())
                .startTime(request.startTime())
                .endTime(request.endTime())
                .clientMemo(request.clientMemo())
                .brokerMemo(request.broker_memo())
                .status(ReservationStatus.CREATED.getValue())
                .build();

        reservationMapper.updateReservation(reservation);
    }

    @Override
    public Reservation getReservation(Long rid) {
        return reservationMapper.findReservationById(rid).orElseThrow(() -> new NotFoundReservation(rid));
    }

    @Override
    public List<Reservation> getReservationsByMember(String memberId) {
        List<Reservation> allReservation = reservationMapper.findAllReservationsByMemberId(memberId);
        if (allReservation.isEmpty()) throw new NotFoundReservation();
        return allReservation;
    }
}
