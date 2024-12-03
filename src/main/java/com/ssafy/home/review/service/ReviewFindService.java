package com.ssafy.home.review.service;

import com.ssafy.home.domain.Broker;
import com.ssafy.home.domain.Member;
import com.ssafy.home.review.dto.ReviewResponse;
import com.ssafy.home.review.dto.ReviewScoreResponse;
import com.ssafy.home.review.repository.ReviewFindMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewFindService {

    private final ReviewFindMapper reviewFindMapper;

    public List<ReviewResponse> findAllMemberReview(Member member) {
        List<ReviewResponse> reviews = reviewFindMapper.findAllMemberReview(member.getMid());
        return reviews;
    }

    public List<ReviewResponse> findAllBrokerReview(Broker broker) {
        List<ReviewResponse> reviews = reviewFindMapper.findAllBrokerReview(broker.getBid());
        return reviews;
    }

    public ReviewScoreResponse findReviewScore(Broker broker) {
        ReviewScoreResponse response = reviewFindMapper.findReviewScore(broker.getBid());
        return response;
    }
}
