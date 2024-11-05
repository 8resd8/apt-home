package com.ssafy.home.auth.dto;

public record LoginDtoResponse(
        String id,
        String name,
        String email,
        String userType, // member 또는 broker
        String sessionId
) { }