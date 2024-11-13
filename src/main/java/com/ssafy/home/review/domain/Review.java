package com.ssafy.home.review.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@ToString
@Builder
public class Review {
    private Long reviewId;
    private Long reservationId;
    private String memberId;
    private String reviewContent;
    private String brokerComment;
    private int reviewRating;
    private Timestamp createdAt;
}
