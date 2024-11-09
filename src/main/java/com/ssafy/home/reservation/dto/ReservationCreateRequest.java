package com.ssafy.home.reservation.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record ReservationCreateRequest(
        @NotNull(message = "{required.field}")
        String brokerId,

        @NotNull(message = "{required.field}")
        List<Long> estateIds,

        @NotNull(message = "{required.field}")
        LocalDateTime startTime,

        @NotNull(message = "{required.field}")
        LocalDateTime endTime,

        String clientMemo,
        String broker_memo
) {
}
