package com.ssafy.home.global.enums;

import lombok.Getter;

@Getter
public enum Email {
    TITLE("[SSAFY] 집이지 비밀번호 재설정 인증코드");


    private final String value;

    Email(String value) {
        this.value = value;
    }
}
