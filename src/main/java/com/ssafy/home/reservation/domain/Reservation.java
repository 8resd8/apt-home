package com.ssafy.home.reservation.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Builder
@ToString
public class Reservation {
    private Long rid;
    private String memberId;
    private String brokerId;
    private Timestamp startTime;
    private Timestamp endTime;
    private String status;
    private String clientMemo;
    private String brokerMemo;
    private String brokerName;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
