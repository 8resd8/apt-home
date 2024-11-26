package com.ssafy.home.reservation.dto;

public record ReservationMemberResponse(
        Long rid, // 예약 ID
        String startTime, // 예약 시작 시간
        String endTime, // 예약 종료 시간
        String brokerName,
        String brokerPhoneNumber,
        String brokerAddress,
        String brokerImage,
        String brokerMemo,
        String brokerEmail,
        String memberImage,
        String memberMemo,
        String memberName,
        String status
) {
}
