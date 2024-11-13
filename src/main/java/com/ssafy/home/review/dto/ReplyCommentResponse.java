package com.ssafy.home.review.dto;

import java.time.LocalDateTime;

public record ReplyCommentResponse(
        Long reviewId,
        String replyComment,
        LocalDateTime brokerRepliedAt
) {
}
