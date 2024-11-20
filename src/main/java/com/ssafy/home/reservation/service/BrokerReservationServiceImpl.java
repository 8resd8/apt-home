package com.ssafy.home.reservation.service;

import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.reservation.repository.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ssafy.home.global.enums.ReservationStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class BrokerReservationServiceImpl implements BrokerReservationService {

    private final ReservationHelper reservationHelper;


    @Override
    public void reserveReservation(Long reservationId, Broker broker, String brokerMemo) {
        // 생성 -> 확정
        reservationHelper.updateStatus(reservationId, broker, RESERVE, brokerMemo);
    }

    @Override
    public void completeReservation(Long reservationId, Broker broker) {
        // 확정 -> 완료 상태로 변경
        reservationHelper.updateStatus(reservationId, broker, COMPLETE, null);

    }

    @Override
    public void cancelReservation(Long reservationId, Broker broker) {
        // 생성 -> 취소 상태로 변경
        reservationHelper.updateStatus(reservationId, broker, CANCEL, null);
    }
}
