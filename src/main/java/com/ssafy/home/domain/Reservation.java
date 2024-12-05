package com.ssafy.home.domain;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Builder
public class Reservation {
    private Long rid;
    private String memberId;
    private String brokerId;
    private Date startTime;
    private Time endTime;
    private String status;
    private String clientMemo;
    private String brokerMemo;
    private String brokerName;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
