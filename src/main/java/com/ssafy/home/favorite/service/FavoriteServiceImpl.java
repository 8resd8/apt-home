package com.ssafy.home.favorite.service;


import com.ssafy.home.favorite.domain.Favorite;
import com.ssafy.home.favorite.dto.FavoriteAddRequest;
import com.ssafy.home.favorite.exception.DeleteFailException;
import com.ssafy.home.favorite.exception.DuplicateFavoriteException;
import com.ssafy.home.favorite.repository.FavoriteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteMapper favoriteMapper;

    @Override
    public void addFavorite(FavoriteAddRequest request) {
        if (favoriteMapper.findFavorite(request.memberId(), request.estateId()).isPresent()) {
            throw new DuplicateFavoriteException("이미 찜 목록에 존재하는 항목입니다.");
        }

        favoriteMapper.insertFavorite(request.memberId(), request.estateId());
    }

    @Override
    public void removeFavorite(FavoriteAddRequest request) {
        if (favoriteMapper.deleteFavorite(request.memberId(), request.estateId()) == 0) {
            throw new DeleteFailException("삭제 실패했습니다.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Favorite> getFavoriteAll(String memberId) {
        return favoriteMapper.findAllFavoriteByMemberId(memberId);
    }
}
