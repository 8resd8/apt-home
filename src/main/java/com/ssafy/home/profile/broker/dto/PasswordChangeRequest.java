package com.ssafy.home.profile.broker.dto;

public record PasswordChangeRequest(
        String memberId,
        String currentPassword,
        String newPassword
) {
}
