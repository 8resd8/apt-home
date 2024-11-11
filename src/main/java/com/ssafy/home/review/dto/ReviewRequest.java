package com.ssafy.home.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ReviewRequest(
        @NotNull(message = "${required.filed}") String brokerId,
        @NotNull(message = "${required.filed}") @Min(1) @Max(5) Integer reviewRating,
        String reviewContent
) {
}
