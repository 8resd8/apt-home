package com.ssafy.home.reservation.service;

import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.reservation.exception.ReservationStatusException;
import com.ssafy.home.reservation.repository.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ssafy.home.global.enums.ReservationStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class BrokerReservationServiceImpl implements BrokerReservationService {

    private final ReservationMapper reservationMapper;

    @Override
    public void reserveReservation(Long reservationId, Broker broker, String brokerMemo) {
        int isSuccess = reservationMapper.updateReserveStatus(
                reservationId, broker.getBid(), RESERVE.getValue(), brokerMemo, CREATE.getValue());

        if (isSuccess == 0) {
            throw new ReservationStatusException(RESERVE.getValue());
        }
    }

    @Override
    public void completeReservation(Long reservationId, Broker broker) {
        int isSuccess = reservationMapper.updateCompleteStatus(
                reservationId, broker.getBid(), COMPLETE.getValue(), RESERVE.getValue());

        if (isSuccess == 0) {
            throw new ReservationStatusException(COMPLETE.getValue());
        }
    }
}
