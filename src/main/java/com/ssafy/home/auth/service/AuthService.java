package com.ssafy.home.auth.service;

import com.ssafy.home.auth.dto.RequestBrokerSignUp;
import com.ssafy.home.auth.dto.RequestMemberSignUp;
import com.ssafy.home.auth.dto.ResponseSignUp;


public interface AuthService {
    ResponseSignUp signUpMember(RequestMemberSignUp requestDto);


    ResponseSignUp signUpBroker(RequestBrokerSignUp requestDto);
}
