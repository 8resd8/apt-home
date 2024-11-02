package com.ssafy.home.auth.domain;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class Member {
    private String id;
    private String password;
    private String salt;
    private String email;
    private String phoneNum;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;
}
