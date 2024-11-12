package com.ssafy.home.profile.member.dto;

public record PasswordChangeRequest(
        String memberId,
        String currentPassword,
        String newPassword
) {
}
