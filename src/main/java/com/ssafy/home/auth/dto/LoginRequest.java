package com.ssafy.home.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "${required.field}")
        String id,

        @NotBlank(message = "${required.field}")
        String password
) {}
