package com.ssafy.home.reservation.service;

import com.ssafy.home.domain.Broker;
import com.ssafy.home.global.enums.ReservationStatus;
import com.ssafy.home.reservation.exception.UpdateStatusReservationException;
import com.ssafy.home.reservation.repository.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationHelper {

    private final ReservationMapper reservationMapper;

    public void updateStatus(Long reservationId, Broker broker, ReservationStatus targetStatus, String brokerMemo) {
        int updateSuccess = reservationMapper.updateReservationStatusByBroker(
                reservationId, broker.getBid(), targetStatus.getValue(), brokerMemo);

        if (updateSuccess == 0) {
            throw new UpdateStatusReservationException(targetStatus.getValue());
        }
    }
}
