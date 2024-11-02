package com.ssafy.home.auth.service;

import com.ssafy.home.auth.dto.*;


public interface AuthService {
    ResponseSignUp signUpMember(RequestMemberSignUp requestDto);

    ResponseSignUp signUpBroker(RequestBrokerSignUp requestDto);

    ResponseLoginDto login(RequestLoginDto requestLoginDto);
}
