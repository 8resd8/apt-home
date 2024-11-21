package com.ssafy.home.reservation.dto;

import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public record ReservationCreateRequest(
        @NotNull(message = "{required.field}")
        String brokerId,

        @NotNull(message = "{required.field}")
        List<Long> estateIds,

        @NotNull(message = "{required.field}")
        Date startTime,

        @NotNull(message = "{required.field}")
        Time endTime,

        String clientMemo
) {
}
