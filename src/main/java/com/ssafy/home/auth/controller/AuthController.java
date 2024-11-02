package com.ssafy.home.auth.controller;

import com.ssafy.home.auth.dto.*;
import com.ssafy.home.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup/member")
    public ResponseEntity<ResponseSignUp> signUpMember(@Validated @RequestBody RequestMemberSignUp requestDto) {
        ResponseSignUp responseMember = authService.signUpMember(requestDto);

        return new ResponseEntity<>(responseMember, HttpStatus.CREATED);
    }

    @PostMapping("/signup/broker")
    public ResponseEntity<ResponseSignUp> signUpBroker(@Validated @RequestBody RequestBrokerSignUp requestDto) {
        ResponseSignUp responseBroker = authService.signUpBroker(requestDto);
        return new ResponseEntity<>(responseBroker, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseLoginDto> login(@Validated @RequestBody RequestLoginDto requestLoginDto) {
        ResponseLoginDto responseLogin = authService.login(requestLoginDto);
        return new ResponseEntity<>(responseLogin, HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        authService.logout();
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }
}
