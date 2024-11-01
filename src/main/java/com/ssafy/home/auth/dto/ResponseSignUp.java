package com.ssafy.home.auth.dto;

import java.time.LocalDateTime;


public record ResponseSignUp(String id, String email, LocalDateTime createdAt) {
}
