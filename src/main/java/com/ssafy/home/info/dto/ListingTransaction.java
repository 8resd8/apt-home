package com.ssafy.home.info.dto;

public record ListingTransaction(
        String year,
        String month,
        Long amount
) {
}