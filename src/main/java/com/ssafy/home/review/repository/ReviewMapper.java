package com.ssafy.home.review.repository;

import com.ssafy.home.review.domain.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.Optional;

@Mapper
public interface ReviewMapper {
    void insertReview(
            @Param("reservationId") Long reservationId,
            @Param("memberId") String memberId,
            @Param("brokerId") String brokerId,
            @Param("reviewContent") String reviewContent,
            @Param("reviewRating") Integer reviewRating
    );

    int updateReview(
            @Param("reservationId") Long reservationId,
            @Param("reviewContent") String reviewContent,
            @Param("reviewRating") Integer reviewRating
    );

    Optional<Review> findReviewById(@Param("reviewId") Long rId);

    void insertReply(@Param("reviewId") Long reviewId, @Param("comment") String comment, @Param("now") LocalDateTime now);

    void updateReply(@Param("reviewId") Long reviewId, @Param("comment") String comment, @Param("now") LocalDateTime now);
}
