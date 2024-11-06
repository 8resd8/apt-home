package com.ssafy.home.favorite.dto;

import jakarta.validation.constraints.NotNull;

public record FavoriteAddRequest(
        @NotNull String memberId,
        @NotNull Long estateId
) {
}
