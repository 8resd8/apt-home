package com.ssafy.home.profile.member.dto;

public record PasswordChangeRequest(
        String currentPassword,
        String password1,
        String password2
) {
}
