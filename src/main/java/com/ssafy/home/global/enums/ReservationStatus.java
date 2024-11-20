package com.ssafy.home.global.enums;

import lombok.Getter;

@Getter
public enum ReservationStatus {
    CREATE("생성"),
    RESERVE("확정"),
    COMPLETE("완료"),
    CANCEL("취소");

    private final String value;

    ReservationStatus(String value) {
        this.value = value;
    }
}
