package com.ssafy.home.review.controller;

import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.global.annotation.Login;
import com.ssafy.home.review.dto.ReviewCreateRequest;
import com.ssafy.home.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final ChatModel chatModel;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveReview(@Login Member member, ReviewCreateRequest request) {
        reviewService.addReview(member, request);
    }

    @PostMapping("/ai")
    @ResponseStatus(HttpStatus.CREATED)
    public String aiContent(@Login Member member) {
        String call = chatModel.call("1+1");
        return call;
    }
}
