package com.ssafy.home.reservation.service;

import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.reservation.domain.Reservation;
import com.ssafy.home.reservation.exception.UpdateStatusReservationException;
import com.ssafy.home.reservation.repository.ReservationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
    private Broker broker;

    Reservation reservation;

    @BeforeEach
    void setUp() {
        reservationMapper = Mockito.mock(ReservationMapper.class);
        reservationHelper = new ReservationHelper(reservationMapper);
        broker = Broker.builder().bid("broker123").build();

        reservation = Reservation.builder()
                .memberId("member123")
                .brokerId(broker.getBid())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now())
                .clientMemo(null)
                .status(CREATE.getValue())
                .build();
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

        verify(reservationMapper, times(1)).updateReservationStatusByBroker(1L, "broker123", RESERVE.getValue(), "확정 메모");
    }

    @Test
    @DisplayName("예약 메모 없이 생성 성공")
    void UpdateStatus_Success_WithoutMemo() {
        when(reservationMapper.updateReservationStatusByBroker(anyLong(), anyString(), eq("완료"), isNull()))
                .thenReturn(1);

        assertDoesNotThrow(() ->
                reservationHelper.updateStatus(2L, broker, COMPLETE, null)
        );

        verify(reservationMapper, times(1)).updateReservationStatusByBroker(2L, "broker123", "완료", null);
    }

    @Test
    @DisplayName("생성 -> 완료 실패")
    void UpdateStatus_Failure() {
        assertThrows(UpdateStatusReservationException.class, () -> reservationHelper.updateStatus(reservation.getRid(), broker, COMPLETE, null));
    }
}
