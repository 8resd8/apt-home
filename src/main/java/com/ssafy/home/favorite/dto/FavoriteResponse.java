package com.ssafy.home.favorite.dto;

import java.sql.Timestamp;
import java.util.List;

public record FavoriteResponse(
        Long fid,
        String memberId,
        Long estateId,
        Timestamp createdAt,
        Timestamp updatedAt,
        List<String> brokerId
) {
}
