package com.ssafy.home.reservation.dto;

import java.time.LocalDateTime;

public record ReservationResponse(
        Long rid,
        String memberId,
        String brokerId,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String status,
        String clientMemo,
        String brokerMemo,
        String brokerName
) {
}
