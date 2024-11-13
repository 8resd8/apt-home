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


    public static Broker toEntity(String id, String officeName, String brokerName, String phoneNum,
                                  String address, String licenseNum, String hashedPassword, String salt, String email) {
        return Broker.builder()
                .bid(id)
                .officeName(officeName)
                .brokerName(brokerName)
                .phoneNum(phoneNum)
                .address(address)
                .licenseNum(licenseNum)
                .password(hashedPassword)
                .salt(salt)
                .email(email)
                .createdAt(null)       // 자동 생성
                .updatedAt(null)       // 자동 생성
                .lastLogin(null)       // 로그인 시 업데이트
                .build();
    }

    public void updateLastLogin() {
        this.lastLogin = LocalDateTime.now();
    }
}
