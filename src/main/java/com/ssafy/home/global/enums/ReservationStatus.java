package com.ssafy.home.global.enums;

import lombok.Getter;

@Getter
public enum ReservationStatus {
    CREATED("Created"),
    RESERVED("Reserved"),
    COMPLETED("Completed");

    private final String value;

    ReservationStatus(String value) {
        this.value = value;
    }
}
