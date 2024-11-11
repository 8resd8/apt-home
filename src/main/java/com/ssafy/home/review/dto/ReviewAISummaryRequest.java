package com.ssafy.home.review.dto;

import jakarta.validation.constraints.NotNull;

public record ReviewAISummaryRequest(
        @NotNull(message = "${required.filed}") Long reservationId,
        String massage
) {
}
