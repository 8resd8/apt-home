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


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveReview(@Login Member member, @Validated @RequestBody ReviewRequest request) {
        reviewService.addReview(member, request);
    }

    @PostMapping("/ai")
    @ResponseStatus(HttpStatus.CREATED)
    public String aiContent(@Login Member member, @Validated @RequestBody ReviewAISummaryRequest aiRequest) {
        return reviewService.addAIReview(member, aiRequest);
    }
}
