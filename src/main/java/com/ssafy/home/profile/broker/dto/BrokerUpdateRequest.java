package com.ssafy.home.profile.broker.dto;

public record BrokerUpdateRequest(
        String name,
        String password,
        String phoneNum
) {
}
