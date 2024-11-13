package com.ssafy.home.auth.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@ToString
@Builder
public class Member {
    private String mid;
    private String password;
    private String salt;
    private String email;
    private String phoneNum;
    private String name;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private LocalDateTime lastLogin;

    public static Member toEntity(String id, String hashedPassword, String salt, String email, String phoneNum, String name) {
        return Member.builder()
                .mid(id)
                .password(hashedPassword)
                .salt(salt)
                .email(email)
                .phoneNum(phoneNum)
                .name(name)
                .createdAt(null)       // 자동 생성
                .updatedAt(null)       // 자동 생성
                .lastLogin(null)       // 로그인 시 업데이트됨
                .build();
    }

    public void updateLastLogin() {
        this.lastLogin = LocalDateTime.now();
    }
}
