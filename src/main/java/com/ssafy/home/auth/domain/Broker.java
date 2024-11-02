package com.ssafy.home.auth.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
public class Broker {
    private String id;
    private String officeName;
    private String name;
    private String phoneNum;
    private String address;
    private String licenseNum;
    private String password;
    private String salt;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;
}
