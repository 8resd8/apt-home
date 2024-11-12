package com.ssafy.home.profile.member.dto;

import java.time.LocalDateTime;

public record MemberProfileResponse(
        String memberId,
        String phoneNum,
        String email,
        LocalDateTime lastLogin,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
