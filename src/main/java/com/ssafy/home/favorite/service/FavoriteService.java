package com.ssafy.home.favorite.service;

import com.ssafy.home.favorite.domain.Favorite;
import com.ssafy.home.favorite.dto.FavoriteAddRequest;

import java.util.List;

public interface FavoriteService {
    void addFavorite(FavoriteAddRequest request);

    void removeFavorite(FavoriteAddRequest request);

    List<Favorite> getFavoriteAll(String memberId);
}
