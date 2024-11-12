package com.ssafy.home.profile.member.dto;

import jakarta.validation.constraints.Email;

public record PasswordResetRequest(@Email String email) {
}
