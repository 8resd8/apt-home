package com.ssafy.home.favorite.controller;

import com.ssafy.home.domain.Member;
import com.ssafy.home.estate.dto.EstateFindResponse;
import com.ssafy.home.estate.service.EstateService;
import com.ssafy.home.favorite.dto.FavoriteAddRequest;
import com.ssafy.home.favorite.service.FavoriteService;
import com.ssafy.home.global.annotation.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/favorite")
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final EstateService estateService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // code: 201
    public void addFavorite(@Login Member member, @Validated @RequestBody FavoriteAddRequest request) {
        favoriteService.addFavorite(member, request);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT) // code: 204
    public void removeFavorite(@Login Member member, @Validated @RequestBody FavoriteAddRequest request) {
        favoriteService.removeFavorite(member, request);
    }

    @GetMapping
    public ResponseEntity<List<EstateFindResponse>> getFavoriteAll(@Login Member member) {
        List<EstateFindResponse> favorites = estateService.findFavoritesByMemberId(member);
        return ResponseEntity.ok().body(favorites);
    }

}
