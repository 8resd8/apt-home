package com.ssafy.home.review.repository;

import com.ssafy.home.review.domain.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface ReviewMapper {
    void insertReview(
            @Param("reservationId") Long reservationId,
            @Param("memberId") String memberId,
            @Param("brokerId") Long brokerId,
            @Param("reviewContent") String reviewContent,
            @Param("reviewRating") Integer reviewRating
    );

    Optional<Review> findReviewById(@Param("rId") Long rId);
}
