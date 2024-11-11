package com.ssafy.home.favorite.exception;

public class EmptyFavoriteException extends RuntimeException {
    public EmptyFavoriteException(String message) {
        super(message);
    }

    public EmptyFavoriteException() {
        super("찜 목록이 없습니다.");
    }
}
