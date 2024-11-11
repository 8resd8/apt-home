package com.ssafy.home.reservation.service;

import com.ssafy.home.global.enums.ReservationStatus;
import com.ssafy.home.reservation.domain.Reservation;
import com.ssafy.home.reservation.dto.ReservationCreateRequest;
import com.ssafy.home.reservation.dto.ReservationResponse;
import com.ssafy.home.reservation.exception.NotFoundReservation;
import com.ssafy.home.reservation.repository.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
                .status(ReservationStatus.CREATED.getValue())
                .build();

        reservationMapper.updateReservation(reservation);
    }

    @Override
    public ReservationResponse getReservation(Long rid) {
        Reservation reservation = reservationMapper.findReservationById(rid)
                .orElseThrow(() -> new NotFoundReservation(rid));

        return toReservationResponse(reservation);
    }

    @Override
    public List<ReservationResponse> getReservationsByMember(String memberId) {
        List<Reservation> allReservations = reservationMapper.findAllReservationsByMemberId(memberId);

        if (allReservations.isEmpty()) {
            throw new NotFoundReservation();
        }

        List<ReservationResponse> reservationResponses = new ArrayList<>();
        for (Reservation reservation : allReservations) {
            reservationResponses.add(toReservationResponse(reservation));
        }

        return reservationResponses;
    }

    private ReservationResponse toReservationResponse(Reservation reservation) {
        return new ReservationResponse(
                reservation.getRid(),
                reservation.getMemberId(),
                reservation.getBrokerId(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                reservation.getStatus(),
                reservation.getClientMemo(),
                reservation.getBrokerMemo(),
                reservation.getBrokerName()
        );
    }
}
