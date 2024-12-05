package com.ssafy.home.reservation.service;

import com.ssafy.home.domain.Broker;
import com.ssafy.home.domain.Reservation;
import com.ssafy.home.reservation.exception.UpdateStatusReservationException;
import com.ssafy.home.reservation.repository.ReservationMapper;
import com.ssafy.home.util.TestDataEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.ssafy.home.global.enums.ReservationStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
public class ReservationHelperTest {

    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private ReservationHelper reservationHelper;

    private Reservation reservation;
    private Broker broker;
    private String brokerId = "broker1";
    private String memberId = "member1";

    @BeforeEach
    void setUp() {
        reservationMapper = Mockito.mock(ReservationMapper.class);
        reservationHelper = new ReservationHelper(reservationMapper);
        broker = TestDataEntity.broker(brokerId);

        reservation = TestDataEntity.reservation(brokerId, memberId);
        reservationMapper.insertReservationByMember(reservation);
    }

    @Test
    @DisplayName("예약 메모 같이 생성 성공")
    void UpdateStatus_Success_WithMemo() {
        when(reservationMapper.updateReservationStatusByBroker(anyLong(), anyString(), eq(RESERVE.getValue()), anyString()))
                .thenReturn(1);

        assertDoesNotThrow(() ->
                reservationHelper.updateStatus(1L, broker, RESERVE, "확정 메모")
        );

        verify(reservationMapper, times(1)).updateReservationStatusByBroker(1L, brokerId, RESERVE.getValue(), "확정 메모");
    }

    @Test
    @DisplayName("예약 메모 없이 생성 성공")
    void UpdateStatus_Success_WithoutMemo() {
        when(reservationMapper.updateReservationStatusByBroker(anyLong(), anyString(), eq("완료"), isNull()))
                .thenReturn(1);

        assertDoesNotThrow(() ->
                reservationHelper.updateStatus(2L, broker, COMPLETE, null)
        );

        verify(reservationMapper, times(1)).updateReservationStatusByBroker(2L, brokerId, "완료", null);
    }

    @Test
    @DisplayName("생성 -> 완료 실패")
    void UpdateStatus_Failure() {
        assertThrows(UpdateStatusReservationException.class, () -> reservationHelper.updateStatus(reservation.getRid(), broker, COMPLETE, null));
    }
}
