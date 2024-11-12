package com.ssafy.home.profile.member.dto;

public record MemberUpdateRequest(
        String name,
        String password,
        String phoneNum
) {
}
