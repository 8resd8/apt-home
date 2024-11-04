package com.ssafy.home.auth.dto;

import java.sql.Timestamp;


public record ResponseSignUp(String id, String email, Timestamp createdAt) {
}
