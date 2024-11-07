package com.ssafy.home.reservation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record ReservationAddRequest(
        @NotBlank String memberId,
        @NotBlank String brokerId,
        @NotNull Timestamp startTime,
        @NotNull Timestamp endTime,
        String clientMemo
) {
}
