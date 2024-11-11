package com.ssafy.home.review.controller;

import com.ssafy.home.review.dto.ReplyCommentRequest;
import com.ssafy.home.review.dto.ReplyCommentResponse;
import com.ssafy.home.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/broker/review/reply")
@RequiredArgsConstructor
public class BrokerReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{reviewId}")
    public ResponseEntity<ReplyCommentResponse> replyCreateComment(@PathVariable Long reviewId, @Validated @RequestBody ReplyCommentRequest request) {
        return ResponseEntity.ok().body(reviewService.createReply(reviewId, request));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReplyCommentResponse> replyCommentUpdate(@PathVariable Long reviewId, @Validated @RequestBody ReplyCommentRequest request) {
        return ResponseEntity.ok().body(reviewService.updateReply(reviewId, request));
    }
}
