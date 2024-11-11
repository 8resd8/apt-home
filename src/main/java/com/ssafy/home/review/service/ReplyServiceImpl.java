package com.ssafy.home.review.service;

import com.ssafy.home.global.util.PromptGenerator;
import com.ssafy.home.reservation.exception.NotFoundReservation;
import com.ssafy.home.review.domain.Review;
import com.ssafy.home.review.dto.ReplyCommentRequest;
import com.ssafy.home.review.dto.ReplyCommentResponse;
import com.ssafy.home.review.repository.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyServiceImpl implements ReplyService {

    private final ReviewMapper reviewMapper;
    private final ChatModel chatModel;

    @Override
    public ReplyCommentResponse createReply(Long reviewId, ReplyCommentRequest request) {
        LocalDateTime now = LocalDateTime.now();
        reviewMapper.insertReply(reviewId, request.replyComment(), now);

        return new ReplyCommentResponse(reviewId, request.replyComment(), now);
    }

    @Override
    public ReplyCommentResponse updateReply(Long reviewId, ReplyCommentRequest request) {
        LocalDateTime now = LocalDateTime.now();
        reviewMapper.updateReply(reviewId, request.replyComment(), now);

        return new ReplyCommentResponse(reviewId, request.replyComment(), now);
    }

    @Override
    public String createAIReply(Long reviewId) {
        Optional<Review> findReview = reviewMapper.findReviewById(reviewId);
        if (findReview.isEmpty()) {
            throw new NotFoundReservation();
        }

        String message = PromptGenerator.brokerReviewGenerator(findReview.get().getReviewContent());
        reviewMapper.updateReply(reviewId, message, LocalDateTime.now());

        return chatModel.call(message);
    }
}
