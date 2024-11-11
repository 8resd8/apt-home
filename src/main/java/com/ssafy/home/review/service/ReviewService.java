package com.ssafy.home.review.service;

import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.review.dto.ReplyCommentRequest;
import com.ssafy.home.review.dto.ReplyCommentResponse;
import com.ssafy.home.review.dto.ReviewAISummaryRequest;
import com.ssafy.home.review.dto.ReviewRequest;

public interface ReviewService {
    void addReview(Member member, ReviewRequest request);

    String addAIReview(Member member, ReviewAISummaryRequest aiRequest);

    ReplyCommentResponse createReply(Long reviewId, ReplyCommentRequest request);

    ReplyCommentResponse updateReply(Long reviewId, ReplyCommentRequest request);


}
