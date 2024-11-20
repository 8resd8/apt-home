package com.ssafy.home.info.dto;

public record AveragePrice(
        String year,
        String month,
        Long averageAmount
) {
}