package com.ssafy.home.review.controller;

import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.global.annotation.Login;
import com.ssafy.home.review.dto.ReviewAISummaryRequest;
import com.ssafy.home.review.dto.ReviewRequest;
import com.ssafy.home.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/review")
public class MemberReviewController {

    private final ReviewService reviewService;


    @PostMapping("/{reservationId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createReview(@Login Member member, @PathVariable Long reservationId, @Validated @RequestBody ReviewRequest request) {
        reviewService.createReview(member, reservationId, request);
    }

    @PutMapping("/{reservationId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateReview(@Login Member member, @PathVariable Long reservationId, @Validated @RequestBody ReviewRequest request) {
        reviewService.updateReview(member, reservationId, request);
    }


    @PutMapping("/ai")
    @ResponseStatus(HttpStatus.CREATED)
    public String aiContent(@Login Member member, @Validated @RequestBody ReviewAISummaryRequest aiRequest) {
        return reviewService.createAIReviewSummary(member, aiRequest);
    }
}
