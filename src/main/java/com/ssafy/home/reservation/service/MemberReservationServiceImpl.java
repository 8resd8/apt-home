package com.ssafy.home.reservation.service;

import com.ssafy.home.global.enums.ReservationStatus;
import com.ssafy.home.reservation.domain.Reservation;
import com.ssafy.home.reservation.dto.ReservationCreateRequest;
import com.ssafy.home.reservation.dto.ReservationResponse;
import com.ssafy.home.reservation.exception.NotFoundReservationException;
import com.ssafy.home.reservation.repository.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberReservationServiceImpl implements MemberReservationService {

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
                .status(ReservationStatus.CREATE.getValue())
                .build();

        reservationMapper.insertReservationByMember(reservation);

        // 2. 매물 예약 연결
        Long reservationId = reservation.getRid();
        request.estateIds().forEach(estateId ->
                reservationMapper.insertReservationEstateByBroker(reservationId, estateId, request.clientMemo())
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
                .status(ReservationStatus.CREATE.getValue())
                .build();

        int updateSuccess = reservationMapper.updateReservationByMember(reservation);
        if (updateSuccess == 0) {
            throw new NotFoundReservationException("예약 수정에 실패했습니다.");
        }
    }

    @Override
    public ReservationResponse getReservation(Long rid) {
        Reservation reservation = reservationMapper.findReservationById(rid)
                .orElseThrow(() -> new NotFoundReservationException(rid));

        return toReservationResponse(reservation);
    }

    @Override
    public List<ReservationResponse> getReservationsByMember(String memberId) {
        List<Reservation> allReservations = reservationMapper.findAllReservationsByMemberId(memberId);

        if (allReservations.isEmpty()) {
            throw new NotFoundReservationException();
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
