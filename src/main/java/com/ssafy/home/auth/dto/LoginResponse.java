package com.ssafy.home.auth.dto;

public record LoginResponse(
        String id,
        String name,
        String email,
        String userType,
        String sessionId
) { }