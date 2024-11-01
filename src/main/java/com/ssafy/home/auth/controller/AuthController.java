package com.ssafy.home.auth.controller;

import com.ssafy.home.auth.dto.RequestBrokerSignUp;
import com.ssafy.home.auth.dto.RequestMemberSignUp;
import com.ssafy.home.auth.dto.ResponseSignUp;
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
        ResponseSignUp responseDto = authService.signUpMember(requestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED); // code: 201
    }

    @PostMapping("/signup/broker")
    public ResponseEntity<ResponseSignUp> signUpBroker(@Validated @RequestBody RequestBrokerSignUp requestDto) {
        ResponseSignUp responseDto = authService.signUpBroker(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
