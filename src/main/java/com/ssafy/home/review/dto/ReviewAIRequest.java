package com.ssafy.home.review.dto;

import jakarta.validation.constraints.NotNull;

public record ReviewAIRequest(
        @NotNull(message = "${required.filed}") Long reservationId
) {
}
