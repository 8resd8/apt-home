package com.ssafy.home.reservation.service;

import com.ssafy.home.global.enums.ReservationStatus;
import com.ssafy.home.reservation.domain.Reservation;
import com.ssafy.home.reservation.dto.ReservationCreateRequest;
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
    public void createReservation(ReservationCreateRequest request, String memberId) {
        Reservation reservation = Reservation.builder()
                .memberId(memberId)
                .brokerId(request.brokerId())
                .startTime(request.startTime())
                .endTime(request.endTime())
                .clientMemo(request.clientMemo())
                .status(ReservationStatus.NOT_RESERVED.name()) // 기본 상태
                .build();

        reservationMapper.insertReservation(reservation, memberId);

        // 매물 예약 연관 관계 설정
        for (Long estateId : request.estateIds()) {
            reservationMapper.insertReservationEstate(reservation.getRid(), estateId);
        }
    }


    @Override
    public void updateReservation(Long rid, ReservationCreateRequest request, String memberId) {
        Reservation reservation = Reservation.builder()
                .rid(rid)
                .memberId(memberId)
                .startTime(request.startTime())
                .endTime(request.endTime())
                .clientMemo(request.clientMemo())
                .build();
        reservationMapper.updateReservation(reservation);
    }

    @Override
    public void deleteReservation(Long rid) {
        reservationMapper.deleteReservation(rid);
    }

    @Override
    public Reservation getReservation(Long rid) {
        return reservationMapper.findReservationById(rid).orElse(null);
    }

    @Override
    public List<Reservation> getAllReservationByMember(String memberId) {
        return reservationMapper.findAllReservationsByMemberId(memberId);
    }
}
