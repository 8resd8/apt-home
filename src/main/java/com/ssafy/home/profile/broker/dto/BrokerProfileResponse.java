package com.ssafy.home.profile.broker.dto;

import java.time.LocalDateTime;

public record BrokerProfileResponse(
        String brokerId,
        String phoneNum,
        String email,
        LocalDateTime lastLogin,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
