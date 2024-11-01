package com.ssafy.home.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RequestMemberSignUp(
        @NotBlank(message = "{required.field}")
        @Size(min = 5, max = 255, message = "{size.id}")
        String id,

        @NotBlank(message = "{required.field}")
        @Size(min = 8, message = "{size.password}")
        String password,

        @NotBlank(message = "{required.field}")
        @Email(message = "{email.valid}")
        String email,

        @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "{pattern.phoneNum}")
        String phoneNum,

        @NotBlank(message = "{required.field}")
        @Size(max = 45, message = "{size.max}")
        String name
) {}
