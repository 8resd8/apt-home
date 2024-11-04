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
}
