package com.ssafy.home.review.service;

import com.ssafy.home.review.dto.ReplyCommentRequest;
import com.ssafy.home.review.dto.ReplyCommentResponse;

public interface ReplyService {

    ReplyCommentResponse createReply(Long reviewId, ReplyCommentRequest request);

    ReplyCommentResponse updateReply(Long reviewId, ReplyCommentRequest request);

    String createAIReply(Long reviewId);
}
