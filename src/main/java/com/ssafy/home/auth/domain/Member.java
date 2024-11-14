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
    private final String mid;
    private final String password;
    private final String salt;
    private final String email;
    private final String phoneNum;
    private final String name;

    private Timestamp createdAt;
    private Timestamp updatedAt;
    private LocalDateTime lastLogin;

}
