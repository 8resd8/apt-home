package com.ssafy.home.reservation.service;

import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.global.enums.ReservationStatus;
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
                reservationId, broker.getBid(), RESERVED.getValue(), brokerMemo, CREATED.getValue());

        if (isSuccess == 0) {
            throw new ReservationStatusException(RESERVED.getValue());
        }
    }

    @Override
    public void completeReservation(Long reservationId, Broker broker) {
        int isSuccess = reservationMapper.updateCompleteStatus(
                reservationId, broker.getBid(), COMPLETED.getValue(), RESERVED.getValue());

        if (isSuccess == 0) {
            throw new ReservationStatusException(COMPLETED.getValue());
        }
    }
}
