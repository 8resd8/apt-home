package com.ssafy.home.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PasswordResetRequest(
        String memberId,
        @NotBlank(message = "{required.field}") @Size(min = 8, message = "{size.password}") String password,
        @NotBlank(message = "{required.field}") @Size(min = 8, message = "{size.password}") String confirmPassword
) {
}
