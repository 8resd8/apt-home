package com.ssafy.home.review.service;

import com.ssafy.home.global.util.PromptGenerator;
import com.ssafy.home.review.domain.Review;
import com.ssafy.home.review.dto.ReplyCommentRequest;
import com.ssafy.home.review.dto.ReplyCommentResponse;
import com.ssafy.home.review.repository.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyServiceImpl implements ReplyService {

    private final ReviewMapper reviewMapper;
    private final ChatModel chatModel;

    /**
     * 시간 통일을 위해 서버에서 생성 후 db에 직접 값을 넣는다.
     * 공인중개사는 답글을 반드시 적어야하고 공백으로 적을 수 없다.
     */

    @Override
    public ReplyCommentResponse createReply(Long reviewId, ReplyCommentRequest request) {
        LocalDateTime now = LocalDateTime.now();
        reviewMapper.insertReply(reviewId, request.replyComment(), now);

        return new ReplyCommentResponse(reviewId, request.replyComment(), now);
    }

    /**
     * 공인중개사는 리뷰를 항상 수정이 가능하다.
     * 확장 todo: 리뷰 생성일 기준 %d일 뒤부터는 수정이 불가능 하도록 추가
     */

    @Override
    public ReplyCommentResponse updateReply(Long reviewId, ReplyCommentRequest request) {
        LocalDateTime now = LocalDateTime.now();
        reviewMapper.updateReply(reviewId, request.replyComment(), now);

        return new ReplyCommentResponse(reviewId, request.replyComment(), now);
    }

    /**
     * 공인중개사는 항상 답글을 달아야 하기 때문에 AI 도움을 받을 수 있다.
     * 사용자가 입력한 리뷰 내용으로 답글 내용을 추천해주고 문자열을 반환한다.
     */

    @Override
    public String createAIReply(Long reviewId) {
        Optional<Review> findReview = reviewMapper.findReviewById(reviewId);
        if (findReview.isEmpty()) {
            throw new NoSuchElementException("리뷰를 찾을 수 없습니다.");
        }

        String memberReviewComment = findReview.get().getReviewContent();
        String message = PromptGenerator.brokerReviewGenerator(memberReviewComment);

        return chatModel.call(message);
    }
}
