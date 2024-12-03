package com.ssafy.home.favorite.service;

import com.ssafy.home.domain.Member;
import com.ssafy.home.favorite.dto.FavoriteAddRequest;
import com.ssafy.home.favorite.dto.FavoriteResponse;

import java.util.List;

public interface FavoriteService {
    void addFavorite(Member member, FavoriteAddRequest request);

    void removeFavorite(Member member, FavoriteAddRequest request);

    List<FavoriteResponse> getFavoriteAll(String memberId);
}
