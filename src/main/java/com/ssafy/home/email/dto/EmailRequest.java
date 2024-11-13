package com.ssafy.home.email.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailRequest(
        @NotBlank(message = "{required.field}")
        @Email(message = "{email.valid}")
        String email) {
}
