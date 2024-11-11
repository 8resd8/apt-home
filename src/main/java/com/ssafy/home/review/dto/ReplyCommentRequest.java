package com.ssafy.home.review.dto;

import jakarta.validation.constraints.NotBlank;

public record ReplyCommentRequest(
        @NotBlank(message = "${required.filed}") String replyComment
) {
}
