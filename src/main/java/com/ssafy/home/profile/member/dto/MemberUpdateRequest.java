package com.ssafy.home.profile.member.dto;

import jakarta.validation.constraints.NotBlank;

public record MemberUpdateRequest(
        @NotBlank(message = "${required.field}") String name,
        @NotBlank(message = "${required.field}") String password,
        String phoneNum
) {
}
