package com.ssafy.home.review.controller;

import com.ssafy.home.review.dto.ReplyCommentRequest;
import com.ssafy.home.review.dto.ReplyCommentResponse;
import com.ssafy.home.review.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/broker/review/reply")
@RequiredArgsConstructor
public class BrokerReviewController {

    private final ReplyService replyService;


    @PostMapping("/{reviewId}")
    public ResponseEntity<ReplyCommentResponse> replyCreateComment(@PathVariable Long reviewId, @Validated @RequestBody ReplyCommentRequest request) {
        ReplyCommentResponse response = replyService.createReply(reviewId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReplyCommentResponse> replyCommentUpdate(@PathVariable Long reviewId, @Validated @RequestBody ReplyCommentRequest request) {
        ReplyCommentResponse response = replyService.updateReply(reviewId, request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/ai/{reviewId}")
    public ResponseEntity<String> replyCreateAIComment(@PathVariable Long reviewId) {
        String aiReply = replyService.createAIReply(reviewId);

        return ResponseEntity.status(HttpStatus.CREATED).body(aiReply);
    }

}
