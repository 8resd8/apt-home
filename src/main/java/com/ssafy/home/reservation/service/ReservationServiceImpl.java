package com.ssafy.home.reservation.service;

import com.ssafy.home.reservation.domain.Reservation;
import com.ssafy.home.reservation.dto.ReservationAddRequest;
import com.ssafy.home.reservation.repository.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final ReservationMapper reservationMapper;

    @Override
    public void addReservation(ReservationAddRequest request) {
        Reservation reservation = Reservation.builder()
                .memberId(request.memberId())
                .brokerId(request.brokerId())
                .startTime(request.startTime())
                .endTime(request.endTime())
                .clientMemo(request.clientMemo())
                .status("PENDING")
                .build();

        reservationMapper.insertReservation(reservation);
    }

    @Override
    public void updateReservation(Long rid, ReservationAddRequest request) {
        Reservation reservation = Reservation.builder()
                .rid(rid)
                .memberId(request.memberId())
                .brokerId(request.brokerId())
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
    public List<Reservation> getReservationsByMember(String memberId) {
        return reservationMapper.findAllReservationsByMemberId(memberId);
    }
}
