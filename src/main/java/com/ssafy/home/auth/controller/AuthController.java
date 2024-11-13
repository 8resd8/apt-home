package com.ssafy.home.auth.controller;

import com.ssafy.home.auth.dto.*;
import com.ssafy.home.auth.service.AuthFacade;
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

    private final AuthFacade authFacade;

    @PostMapping("/signup/member")
    public ResponseEntity<SignUpResponse> signUpMember(@Validated @RequestBody MemberSignUpRequest request) {
        SignUpResponse responseMember = authFacade.signUpMember(request);

        return new ResponseEntity<>(responseMember, HttpStatus.CREATED);
    }

    @PostMapping("/signup/broker")
    public ResponseEntity<SignUpResponse> signUpBroker(@Validated @RequestBody BrokerSignUpRequest request) {
        SignUpResponse responseBroker = authFacade.signUpBroker(request);

        return new ResponseEntity<>(responseBroker, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Validated @RequestBody LoginRequest request) {
        LoginResponse responseLogin = authFacade.login(request);

        return new ResponseEntity<>(responseLogin, HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout() {
        authFacade.logout();
    }
}
