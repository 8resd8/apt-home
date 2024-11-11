package com.ssafy.home.review.service;

import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.review.dto.ReviewAISummaryRequest;
import com.ssafy.home.review.dto.ReviewRequest;

public interface ReviewService {
    void createReview(Member member, Long reservationId, ReviewRequest request);

    String createAIReviewSummary(Member member, ReviewAISummaryRequest aiRequest);

    void updateReview(Member member, Long reservationId, ReviewRequest request);
}
