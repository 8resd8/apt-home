package com.ssafy.home.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse {
    private Long rid;
    private String memberId;
    private String brokerId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private String clientMemo;
    private String brokerMemo;
    private String brokerName;
    private List<EstateInfo> estates;
}