package com.ssafy.home.global.enums;

import lombok.Getter;

@Getter
public enum ReservationStatus {
    CREATE("Created"),
    RESERVE("Reserved"),
    COMPLETE("Completed"),
    CANCEL("Canceled");

    private final String value;

    ReservationStatus(String value) {
        this.value = value;
    }
}
