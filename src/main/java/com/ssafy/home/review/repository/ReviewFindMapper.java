package com.ssafy.home.review.repository;

import com.ssafy.home.review.dto.ReviewResponse;
import com.ssafy.home.review.dto.ReviewScoreResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewFindMapper {

    List<ReviewResponse> findAllMemberReview(@Param("memberId") String memberId);

    List<ReviewResponse> findAllBrokerReview(@Param("brokerId") String brokerId);

    ReviewScoreResponse findReviewScore(@Param("brokerId") String brokerId);
}
