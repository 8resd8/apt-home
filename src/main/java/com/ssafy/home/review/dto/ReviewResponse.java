package com.ssafy.home.review.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record ReviewResponse(
        Long reviewId,
        String memberName,
        String brokerName,
        String reviewContent,
        String brokerComment,
        int reviewRating,
        Timestamp createdAt,
        String memberImageUrl,
        String brokerImageUrl,
        LocalDateTime brokerRepliedAt
) {
}
