package com.ssafy.home.auth.controller;

import com.ssafy.home.auth.dto.*;
import com.ssafy.home.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup/member")
    public ResponseEntity<SignUpResponse> signUpMember(@Validated @RequestBody MemberSignUpRequest requestDto) {
        SignUpResponse responseMember = authService.signUpMember(requestDto);

        return new ResponseEntity<>(responseMember, HttpStatus.CREATED);
    }

    @PostMapping("/signup/broker")
    public ResponseEntity<SignUpResponse> signUpBroker(@Validated @RequestBody BrokerSignUpRequest requestDto) {
        SignUpResponse responseBroker = authService.signUpBroker(requestDto);
        return new ResponseEntity<>(responseBroker, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDtoResponse> login(@Validated @RequestBody LoginRequest loginRequest) {
        LoginDtoResponse responseLogin = authService.login(loginRequest);
        return new ResponseEntity<>(responseLogin, HttpStatus.CREATED);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<String> logout() {
        authService.logout();
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }

    @DeleteMapping("/delete/{userType}/{userId}")
    public ResponseEntity<String> deleteAccount(@PathVariable String userId, @PathVariable String userType) {
        authService.deleteAccount(userId, userType);
        return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
    }
}
