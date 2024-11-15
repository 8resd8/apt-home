package com.ssafy.home.auth.controller;

import com.ssafy.home.auth.dto.request.BrokerSignUpRequest;
import com.ssafy.home.auth.dto.request.LoginRequest;
import com.ssafy.home.auth.dto.request.MemberSignUpRequest;
import com.ssafy.home.auth.dto.response.LoginResponse;
import com.ssafy.home.auth.dto.response.SignUpResponse;
import com.ssafy.home.auth.service.AuthFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthFacade authFacade;

    @PostMapping("/signup/member")
    public ResponseEntity<SignUpResponse> signUpMember(@Validated @RequestPart("member") MemberSignUpRequest request,
                                                       @RequestPart(value = "profileImage", required = false) MultipartFile profileImage) {

        SignUpResponse responseMember = authFacade.signUpMember(request, profileImage);

        return new ResponseEntity<>(responseMember, HttpStatus.CREATED);
    }

    @PostMapping("/signup/broker")
    public ResponseEntity<SignUpResponse> signUpBroker(@Validated @RequestPart("broker") BrokerSignUpRequest request,
                                                       @RequestPart(value = "profileImage", required = false) MultipartFile profileImage) {
        SignUpResponse responseBroker = authFacade.signUpBroker(request, profileImage);

        return new ResponseEntity<>(responseBroker, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Validated @RequestBody LoginRequest request) {
        LoginResponse responseLogin = authFacade.login(request);

        return new ResponseEntity<>(responseLogin, HttpStatus.OK);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout() {
        authFacade.logout();
    }
}
