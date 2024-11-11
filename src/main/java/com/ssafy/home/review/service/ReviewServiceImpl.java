package com.ssafy.home.review.service;

import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.global.enums.ReservationStatus;
import com.ssafy.home.global.util.PromptGenerator;
import com.ssafy.home.reservation.domain.Reservation;
import com.ssafy.home.reservation.exception.NotFoundReservation;
import com.ssafy.home.reservation.repository.ReservationMapper;
import com.ssafy.home.review.domain.Review;
import com.ssafy.home.review.dto.ReviewAISummaryRequest;
import com.ssafy.home.review.dto.ReviewRequest;
import com.ssafy.home.review.exception.DuplicateReviewException;
import com.ssafy.home.review.exception.NotCompleteReviewException;
import com.ssafy.home.review.repository.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;
    private final ReservationMapper reservationMapper;
    private final ChatModel chatModel;

    @Override
    public void addReview(Member member, ReviewRequest request) {
        isValidReview(request);

        // 리뷰 작성
        reviewMapper.insertReview(
                request.reservationId(),
                member.getMid(),
                request.brokerId(),
                request.reviewContent(),
                request.reviewRating()
        );
    }

    private void isValidReview(ReviewRequest request) {
        // 1. 예약 존재 확인
        Optional<Reservation> findReservation = reservationMapper.findReservationById(request.reservationId());

        if (findReservation.isEmpty()) {
            throw new NotFoundReservation();
        }

        Reservation reservation = findReservation.get();

        // 2. 예약 Status 확인 (Completed)
        if (!reservation.getStatus().equals(ReservationStatus.COMPLETED.toString())) {
            throw new NotCompleteReviewException();
        }

        // 3. 이미 남겨진 리뷰인지 확인
        Optional<Review> findReview = reviewMapper.findReviewById(request.reservationId());

        if (findReview.isEmpty()) {
            throw new DuplicateReviewException();
        }
    }

    @Override
    public String addAIReview(Member member, ReviewAISummaryRequest aiRequest) {
        String prompt = PromptGenerator.userMassageSummary(member, aiRequest.massage());
        return chatModel.call(prompt);
    }
}
