package com.ssafy.home.review.dto;

import jakarta.validation.constraints.NotBlank;

public record ReviewAISummaryRequest(
        @NotBlank(message = "${required.filed}") String massage
) {
}
