package com.ssafy.home.review.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ReviewCreateRequest(
        @NotNull(message = "${required.filed}") Long reservationId,
        @NotNull(message = "${required.filed}") @Size(min = 1, max = 5) Integer reviewRating,
        String reviewContent
) {
}
