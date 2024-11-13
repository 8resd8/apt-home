package com.ssafy.home.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailCodeRequest(
        @NotBlank(message = "{required.field}")
        @Email(message = "{email.valid}")
        String email,
        @NotBlank(message = "{required.field}")
        String authCode
) {
}
