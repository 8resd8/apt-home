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
import com.ssafy.home.review.exception.CannotUpdateReviewException;
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
    public void createReview(Member member, Long reservationId, ReviewRequest request) {
        isValidReview(request, reservationId);

        reviewMapper.insertReview(
                reservationId,
                member.getMid(),
                request.brokerId(),
                request.reviewContent(),
                request.reviewRating()
        );
    }

    private void isValidReview(ReviewRequest request, Long reservationId) {
        // 1. 예약 존재 확인
        Optional<Reservation> findReservation = reservationMapper.findReservationById(reservationId);

        if (findReservation.isEmpty()) {
            throw new NotFoundReservation();
        }

        Reservation reservation = findReservation.get();

        // 2. 예약 Status 확인 (Completed)
        if (!reservation.getStatus().equals(ReservationStatus.COMPLETED.getValue())) {
            throw new NotCompleteReviewException();
        }

        // 3. 이미 남겨진 리뷰인지 확인
        Optional<Review> findReview = reviewMapper.findReviewById(reservationId);

        if (findReview.isPresent()) {
            throw new DuplicateReviewException();
        }
    }

    @Override
    public void updateReview(Member member, Long reservationId, ReviewRequest request) {
        int update = reviewMapper.updateReview(reservationId, request.reviewContent(), request.reviewRating());

        if (update == 0) {
            throw new CannotUpdateReviewException();
        }
    }


    @Override
    public String createAIReviewSummary(Member member, ReviewAISummaryRequest aiRequest) {
        String prompt = PromptGenerator.userMassageSummary(member, aiRequest.massage());
        return chatModel.call(prompt);
    }
}
