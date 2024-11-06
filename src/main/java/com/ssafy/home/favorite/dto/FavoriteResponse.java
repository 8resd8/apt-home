package com.ssafy.home.favorite.dto;

import java.time.LocalDateTime;

public record FavoriteResponse(
        Long fid,
        String memberId,
        Long estateId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}