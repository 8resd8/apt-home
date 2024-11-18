package com.ssafy.home.favorite.service;

import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.favorite.domain.Favorite;
import com.ssafy.home.favorite.dto.FavoriteAddRequest;
import com.ssafy.home.favorite.dto.FavoriteResponse;
import com.ssafy.home.favorite.exception.DeleteFailException;
import com.ssafy.home.favorite.exception.DuplicateFavoriteException;
import com.ssafy.home.favorite.repository.FavoriteMapper;
import com.ssafy.home.favorite.service.data.TestDataFavoriteRequest;
import com.ssafy.home.favorite.service.data.TestDataResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.ssafy.home.util.TestDataEntity.favorite;
import static com.ssafy.home.util.TestDataEntity.member;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FavoriteServiceImplTest {

    @Mock
    private FavoriteMapper favoriteMapper; // 그 안에 Mapper를 주입, Autowired로 넣어줄 값

    @InjectMocks
    private FavoriteServiceImpl favoriteService; // FavoriteService 객체를 만든다, 하나의 클래스


    private Member member;
    private Favorite favorite;
    private FavoriteAddRequest request;
    private FavoriteResponse response;

    private String memberId = "member1";
    private String brokerId = "broker1";
    private Long estateId = 1L;


    @BeforeEach
    void setUp() {
        member = member(memberId);
        favorite = favorite(memberId, estateId);
        request = TestDataFavoriteRequest.favoriteAddRequest(estateId);
        response = TestDataResponse.favoriteResponse(memberId, brokerId, estateId);
    }

    @Test
    @DisplayName("찜 목록 추가 성공")
    void addFavoriteSuccess() {
        // 원래 나와야 하는 값 임의 설정
        when(favoriteMapper.findFavorite(memberId, estateId)).thenReturn(Optional.empty()); // 값이 없는 상태
        when(favoriteMapper.insertFavorite(memberId, estateId)).thenReturn(1);

        // 실제 수행하기
        assertDoesNotThrow(() -> favoriteService.addFavorite(member, request)); // Junit, addFavorite를 수행하면 예외가 발생하지 않음

        verify(favoriteMapper, times(1)).insertFavorite("member1", estateId); // 1회 호출되었는지 확인용도?
        verify(favoriteMapper, times(1)).findFavorite(memberId, estateId);
    }

    @Test
    @DisplayName("찜 목록 추가 실패")
    void addFavoriteFail() {
        // 원래 나와야 하는 값 임의 설정
        when(favoriteMapper.findFavorite(memberId, estateId)).thenReturn(Optional.of(favorite)); // 값이 존재

        // 실제 수행하기
        assertThrows(DuplicateFavoriteException.class, () -> favoriteService.addFavorite(member, request)); // DuplicateFavoriteException 예외가 발생해야함.

        verify(favoriteMapper, times(1)).findFavorite(memberId, estateId); // 이건 1회 호출되어야함.
        verify(favoriteMapper, never()).insertFavorite(memberId, estateId); // 호출되면 안된다.
    }

    @Test
    @DisplayName("찜 제거 성공")
    void removeFavoriteSuccess() {
        when(favoriteMapper.deleteFavorite(member.getMid(), request.estateId())).thenReturn(1);

        assertDoesNotThrow(() -> favoriteService.removeFavorite(member, request));

        verify(favoriteMapper, times(1)).deleteFavorite(member.getMid(), request.estateId());
    }

    @Test
    @DisplayName("찜 제거 실패")
    void removeFavoriteFail() {
        when(favoriteMapper.deleteFavorite(member.getMid(), request.estateId())).thenReturn(0);

        assertThrows(DeleteFailException.class, () -> favoriteService.removeFavorite(member, request));

        verify(favoriteMapper, times(1)).deleteFavorite(memberId, estateId);
    }

    @Test
    @DisplayName("전체 찜 목록 조회 - 1개이상")
    void getFavoriteAllSuccess() {
        when(favoriteMapper.findAllFavorite(member.getMid())).thenReturn(List.of(response));

        List<FavoriteResponse> favorites = favoriteService.getFavoriteAll(member.getMid());
        assertThat(favorites).isNotNull();
        assertThat(favorites).hasSizeGreaterThanOrEqualTo(1);

        verify(favoriteMapper, times(1)).findAllFavorite(member.getMid());
    }

    @Test
    @DisplayName("전체 찜 목록 조회 - 0개")
    void getFavoriteAll_Empty() {
        when(favoriteMapper.findAllFavorite(member.getMid())).thenReturn(List.of());

        List<FavoriteResponse> favorites = favoriteService.getFavoriteAll(member.getMid());
        assertThat(favorites).isNotNull();
        assertThat(favorites).isEmpty();

        verify(favoriteMapper, times(1)).findAllFavorite(member.getMid());
    }

}