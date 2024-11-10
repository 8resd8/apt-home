package com.ssafy.home.review.service;

import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.review.dto.ReviewAIRequest;
import com.ssafy.home.review.dto.ReviewRequest;

public interface ReviewService {
    void addReview(Member member, ReviewRequest request);

    String addAIReview(Member member, ReviewAIRequest aiRequest);
}
