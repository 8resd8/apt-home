package com.ssafy.home.review.service;

import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.review.domain.Review;
import com.ssafy.home.review.dto.ReviewCreateRequest;
import com.ssafy.home.review.exception.DuplicateReviewException;
import com.ssafy.home.review.repository.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;
//    private final ReservationMapper reservationMapper;

    @Override
    public void addReview(Member member, ReviewCreateRequest request) {
        // 예약 존재 확인
//        Reservation reservation = reservationMapper.findById(request.reservationId());

        // 예약 Status 확인 (Completed)

        // 이미 남겨진 리뷰인지 확인
        Optional<Review> findReview = reviewMapper.findReviewById(request.reservationId());

        if (findReview.isEmpty()) throw new DuplicateReviewException();

        // 리뷰 작성
        reviewMapper.insertReview(
                request.reservationId(),
                member.getMid(),
                request.brokerId(),
                request.reviewContent(),
                request.reviewRating()
        );
    }
}
