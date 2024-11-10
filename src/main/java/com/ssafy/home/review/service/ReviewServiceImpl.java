package com.ssafy.home.review.service;

import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.review.domain.HouseInfo;
import com.ssafy.home.review.domain.Review;
import com.ssafy.home.review.dto.ReviewAIRequest;
import com.ssafy.home.review.dto.ReviewRequest;
import com.ssafy.home.review.exception.DuplicateReviewException;
import com.ssafy.home.review.repository.HouseInfoMapper;
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
    private final HouseInfoMapper houseInfoMapper;
    private final ChatModel chatModel;

    @Override
    public void addReview(Member member, ReviewRequest request) {
        // 예약 존재 확인
//        1Reservation reservation = reservationMapper.findById(request.reservationId());

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

    @Override
    public String addAIReview(Member member, ReviewAIRequest aiRequest) {
        HouseInfo houseInfo = houseInfoMapper.findHouseInfoById(aiRequest.reservationId());
        String prompt = getPrompt(member, houseInfo);

        return chatModel.call(prompt);
    }

    private static String getPrompt(Member member, HouseInfo houseInfo) {
        return String.format(
                "Imagine you are writing a thoughtful review for a real estate agent after visiting the apartment below. " +
                        "Express genuine impressions and appreciation for the agent's assistance during the visit.\n" +
                        "Details:\n" +
                        "- Apartment: %s\n" +
                        "- Address: %s, %s, %s\n" +
                        "- Built in: %d\n" +
                        "- Location: %s, %s\n" +
                        "- Visitor: %s\n\n" +
                        "Mention the apartment's atmosphere, any unique aspects that stood out, and convey your feelings about the overall experience in a friendly and grateful tone, within 30-50 words. For Korean\n",
                houseInfo.getAptNm(),
                houseInfo.getRoadNm(),
                houseInfo.getRoadNmBonbun(),
                houseInfo.getUmdNm(),
                houseInfo.getBuildYear(),
                houseInfo.getLatitude(),
                houseInfo.getLongitude(),
                member.getName()
        );
    }
}
