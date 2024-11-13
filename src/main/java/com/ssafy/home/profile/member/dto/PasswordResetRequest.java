package com.ssafy.home.profile.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PasswordResetRequest(
        @NotBlank(message = "{required.field}") @Size(min = 8, message = "{size.password}") String password,
        @NotBlank(message = "{required.field}") @Size(min = 8, message = "{size.password}") String confirmPassword
) {
}
