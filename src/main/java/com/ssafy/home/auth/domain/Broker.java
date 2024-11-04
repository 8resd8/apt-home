package com.ssafy.home.auth.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@ToString
@Builder
public class Broker {
    private String bid;
    private String officeName;
    private String brokerName;
    private String phoneNum;
    private String address;
    private String licenseNum;
    private String password;
    private String salt;
    private String email;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private LocalDateTime lastLogin;
}
