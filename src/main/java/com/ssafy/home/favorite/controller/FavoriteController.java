package com.ssafy.home.favorite.controller;

import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.favorite.domain.Favorite;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // code: 201
    public void addFavorite(@Login Member member, @Validated @RequestBody FavoriteAddRequest request) {
        favoriteService.addFavorite(new FavoriteAddRequest(member.getMid(), request.estateId()));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT) // code: 204
    public void removeFavorite(@Login Member member, @Validated @RequestBody FavoriteAddRequest request) {
        favoriteService.removeFavorite(new FavoriteAddRequest(member.getMid(), request.estateId()));
    }

    // List 브로커 ID도 같이 응답해야한다.
    @GetMapping
    public ResponseEntity<List<Favorite>> getFavoriteAll(@Login Member member) {
        List<Favorite> favorites = favoriteService.getFavoriteAll(member.getMid());
        return ResponseEntity.ok().body(favorites);
    }

}
