package com.ssafy.home.auth.dto;

import java.sql.Timestamp;


public record SignUpResponse(String id, String email, Timestamp createdAt) {
}
