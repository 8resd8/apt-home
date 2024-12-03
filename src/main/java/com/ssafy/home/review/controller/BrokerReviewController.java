package com.ssafy.home.review.controller;

import com.ssafy.home.domain.Broker;
import com.ssafy.home.global.annotation.Login;
import com.ssafy.home.review.dto.ReplyCommentRequest;
import com.ssafy.home.review.dto.ReplyCommentResponse;
import com.ssafy.home.review.dto.ReviewResponse;
import com.ssafy.home.review.dto.ReviewScoreResponse;
import com.ssafy.home.review.service.ReplyService;
import com.ssafy.home.review.service.ReviewFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/broker/review")
@RequiredArgsConstructor
public class BrokerReviewController {

    private final ReplyService replyService;
    private final ReviewFindService reviewFindService;


    @PostMapping("/reply/{reviewId}")
    public ResponseEntity<ReplyCommentResponse> replyCreateComment(@PathVariable Long reviewId, @Validated @RequestBody ReplyCommentRequest request) {
        ReplyCommentResponse response = replyService.createReply(reviewId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/reply/{reviewId}")
    public ResponseEntity<ReplyCommentResponse> replyCommentUpdate(@PathVariable Long reviewId, @Validated @RequestBody ReplyCommentRequest request) {
        ReplyCommentResponse response = replyService.updateReply(reviewId, request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/reply/ai/{reviewId}")
    public ResponseEntity<String> replyCreateAIComment(@PathVariable Long reviewId) {
        String aiReply = replyService.createAIReply(reviewId);

        return ResponseEntity.status(HttpStatus.CREATED).body(aiReply);
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> getReviewsBroker(@Login Broker broker) {
        return ResponseEntity.ok(reviewFindService.findAllBrokerReview(broker));
    }

    @GetMapping("/score")
    public ResponseEntity<ReviewScoreResponse> getReviewScore(@Login Broker broker) {
        return ResponseEntity.ok(reviewFindService.findReviewScore(broker));
    }

}
