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
    private final String bid;
    private final String password;
    private final String salt;
    private final String email;
    private final String phoneNum;
    private final String address;
    private final String licenseNum;
    private final String brokerName;

    private String officeName;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private LocalDateTime lastLogin;

}
