package com.ssafy.home.favorite.dto;

import java.sql.Timestamp;

public record FavoriteResponse(
        Long fid,
        String memberId,
        Long estateId,
        Timestamp createdAt,
        Timestamp updatedAt,
        String brokerId
) {
}
