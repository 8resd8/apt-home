package com.ssafy.home.favorite.controller;

import com.ssafy.home.annotation.Login;
import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.favorite.domain.Favorite;
import com.ssafy.home.favorite.dto.FavoriteAddRequest;
import com.ssafy.home.favorite.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/favorite")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // code: 201
    public void addFavorite(@Login Member member, @RequestBody FavoriteAddRequest request) {
        favoriteService.addFavorite(new FavoriteAddRequest(member.getMid(), request.estateId()));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT) // code: 204
    public void removeFavorite(@Login Member member, @RequestBody FavoriteAddRequest request) {
        favoriteService.removeFavorite(new FavoriteAddRequest(member.getMid(), request.estateId()));
    }

    @GetMapping
    public ResponseEntity<List<Favorite>> getFavoriteAll(@Login Member member) {
        List<Favorite> favorites = favoriteService.getFavoriteAll(member.getMid());
        return ResponseEntity.ok().body(favorites);
    }

}
