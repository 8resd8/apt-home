package com.ssafy.home.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PasswordChangeRequest(
        @NotBlank(message = "{required.field}") @Size(min = 8, message = "{size.password}") String currentPassword,
        @NotBlank(message = "{required.field}") @Size(min = 8, message = "{size.password}") String newPassword1,
        @NotBlank(message = "{required.field}") @Size(min = 8, message = "{size.password}") String newPassword2
) {
}
