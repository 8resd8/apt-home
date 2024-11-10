package com.ssafy.home.review.service;

import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.review.dto.ReviewCreateRequest;

public interface ReviewService {
    void addReview(Member member, ReviewCreateRequest request);
}
