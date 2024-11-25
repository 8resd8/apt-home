package com.ssafy.home.review.dto;

public record ReviewScoreResponse(
        Double reviewAvg,
        Long reviewTotal,
        Long reviewComment
) {
}
