package com.ssafy.home.auth.service;

import com.ssafy.home.auth.dto.*;


public interface AuthService {
    SignUpResponse signUpMember(MemberSignUpRequest requestDto);

    SignUpResponse signUpBroker(BrokerSignUpRequest requestDto);

    LoginResponse login(LoginRequest loginRequest);

    void logout();
}
