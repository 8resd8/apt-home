package com.ssafy.home.review.service;

import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.global.enums.ReservationStatus;
import com.ssafy.home.global.util.PromptGenerator;
import com.ssafy.home.reservation.domain.Reservation;
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

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;
    private final ReservationMapper reservationMapper;
    private final ChatModel chatModel;

    /**
     * 리뷰 생성은 예약 ID를 통해 생성한다.
     * 1. 예약이 실제 있는 경우만 리뷰를 남길 수 있음
     * 2. 이미 존재할 경우 추가 리뷰 생성은 불가능
     * 3. 예약이 완료(Completed)되지 않았다면 리뷰남길 수 없음
     */

    @Override
    public void createReview(Member member, Long reservationId, ReviewRequest request) {
        isValidReview(reservationId);

        reviewMapper.insertReview(
                reservationId,
                member.getMid(),
                request.brokerId(),
                request.reviewContent(),
                request.reviewRating()
        );
    }

    private void isValidReview(Long reservationId) {
        // 1. 예약 존재 확인
        Optional<Reservation> findReservation = reservationMapper.findReservationById(reservationId);

        if (findReservation.isEmpty()) {
            throw new NoSuchElementException("리뷰를 찾을 수 없습니다.");
        }

        Reservation reservation = findReservation.get();

        // 2. 예약 Status 확인 (Completed)
        if (!reservation.getStatus().equals(ReservationStatus.COMPLETE.getValue())) {
            throw new NotCompleteReviewException();
        }

        // 3. 이미 남겨진 리뷰인지 확인
        Optional<Review> findReview = reviewMapper.findReviewById(reservationId);

        if (findReview.isPresent()) {
            throw new DuplicateReviewException();
        }
    }

    /**
     * 리뷰 수정은 공인중개사가 답글을 남기지 않을 때만 수정이 가능하다.
     */

    @Override
    public void updateReview(Member member, Long reservationId, ReviewRequest request) {
        int update = reviewMapper.updateReview(reservationId, request.reviewContent(), request.reviewRating());

        if (update == 0) {
            throw new CannotUpdateReviewException();
        }
    }

    /**
     * 사용자가 작성한 리뷰를 기반으로 내용을 AI 요약 후 문자열을 리턴한다
     */
    @Override
    public String createAIReviewSummary(Member member, ReviewAISummaryRequest aiRequest) {
        String prompt = PromptGenerator.userMassageSummary(member, aiRequest.massage());
        return chatModel.call(prompt);
    }
}
