package com.ssafy.home.profile.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record MemberUpdateRequest(
        @NotBlank(message = "${required.field}") String name,
        @NotBlank(message = "{required.field}") @Size(min = 8, message = "{size.password}") String password,
        @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "{pattern.phoneNum}") String phoneNum
) {
}
