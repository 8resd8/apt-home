package com.ssafy.home.review.controller;

import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.global.annotation.Login;
import com.ssafy.home.review.dto.ReviewCreateRequest;
import com.ssafy.home.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveReview(@Login Member member, ReviewCreateRequest request) {
        reviewService.addReview(member, request);
    }

    @GetMapping("/ai")
    @ResponseStatus(HttpStatus.CREATED)
    public String aiContent(@Login Member member) {
        return "AI가 만든 추천 메시지..";
    }
}
