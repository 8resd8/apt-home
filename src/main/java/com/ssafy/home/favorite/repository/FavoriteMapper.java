package com.ssafy.home.favorite.repository;

import com.ssafy.home.favorite.domain.Favorite;
import com.ssafy.home.favorite.dto.FavoriteResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface FavoriteMapper {

    int insertFavorite(@Param("memberId") String memberId, @Param("estateId") Long estateId);

    int deleteFavorite(@Param("memberId") String memberId, @Param("estateId") Long estateId);

    List<FavoriteResponse> findAllFavorite(@Param("memberId") String memberId);

    Optional<Favorite> findFavorite(@Param("memberId") String memberId, @Param("estateId") Long estateId);


}
