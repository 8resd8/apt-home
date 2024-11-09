package com.ssafy.home.favorite.service;


import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.estate.repository.EstateMapper;
import com.ssafy.home.favorite.domain.Favorite;
import com.ssafy.home.favorite.dto.FavoriteAddRequest;
import com.ssafy.home.favorite.dto.FavoriteResponse;
import com.ssafy.home.favorite.exception.DeleteFailException;
import com.ssafy.home.favorite.exception.DuplicateFavoriteException;
import com.ssafy.home.favorite.exception.EmptyFavoriteException;
import com.ssafy.home.favorite.repository.FavoriteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteMapper favoriteMapper;
    private final EstateMapper estateMapper;

    @Override
    public void addFavorite(Member member, FavoriteAddRequest request) {
        if (favoriteMapper.findFavorite(member.getMid(), request.estateId()).isPresent()) {
            throw new DuplicateFavoriteException("이미 찜 목록에 존재하는 항목입니다.");
        }

        favoriteMapper.insertFavorite(member.getMid(), request.estateId());
    }

    @Override
    public void removeFavorite(Member member, FavoriteAddRequest request) {
        if (favoriteMapper.deleteFavorite(member.getMid(), request.estateId()) == 0) {
            throw new DeleteFailException("삭제 실패했습니다.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<FavoriteResponse> getFavoriteAll(String memberId) {
        List<Favorite> favorites = favoriteMapper.findAllFavoriteByMemberId(memberId);
        if (favorites.isEmpty()) {
            throw new EmptyFavoriteException();
        }


        List<FavoriteResponse> favoriteResponses = new ArrayList<>();
        for (Favorite favorite : favorites) {
            String brokerId = estateMapper.findBrokerIdByEstateId(favorite.getEstateId());

            FavoriteResponse favoriteResponse = new FavoriteResponse(
                    favorite.getFid(),
                    favorite.getMemberId(),
                    favorite.getEstateId(),
                    favorite.getCreatedAt(),
                    favorite.getUpdatedAt(),
                    List.of(brokerId) // brokerId를 리스트 형태로 저장
            );

            favoriteResponses.add(favoriteResponse);
        }

        return favoriteResponses;
    }
}
