package com.ssafy.home.auth.service;

import com.ssafy.home.auth.dto.request.BrokerSignUpRequest;
import com.ssafy.home.auth.dto.request.LoginRequest;
import com.ssafy.home.auth.dto.request.MemberSignUpRequest;
import com.ssafy.home.auth.dto.response.LoginResponse;
import com.ssafy.home.auth.dto.response.SignUpResponse;
import com.ssafy.home.auth.exception.LoginFailedException;
import com.ssafy.home.auth.service.login.BrokerLoginService;
import com.ssafy.home.auth.service.login.LoginHelper;
import com.ssafy.home.auth.service.login.MemberLoginService;
import com.ssafy.home.auth.service.logout.LogoutService;
import com.ssafy.home.auth.service.signup.BrokerSignUpService;
import com.ssafy.home.auth.service.signup.MemberSignUpService;
import com.ssafy.home.global.enums.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AuthFacade {

    private final MemberSignUpService memberSignUpService;
    private final BrokerSignUpService brokerSignUpService;
    private final MemberLoginService memberLoginService;
    private final BrokerLoginService brokerLoginService;
    private final LogoutService logoutService;
    private final LoginHelper loginHelper;

    public SignUpResponse signUpMember(MemberSignUpRequest memberRequest, MultipartFile profileImage) {
        SignUpResponse response = memberSignUpService.signUp(memberRequest, profileImage);
        return response;
    }

    public SignUpResponse signUpBroker(BrokerSignUpRequest brokerRequest, MultipartFile profileImage) {
        SignUpResponse response = brokerSignUpService.signUp(brokerRequest, profileImage);
        return response;
    }

    public LoginResponse login(LoginRequest request) {
        UserType userType = loginHelper.userTypeCheck(request.id());

        if (userType == UserType.MEMBER) {
            return memberLoginService.login(request);
        }

        if (userType == UserType.BROKER) {
            return brokerLoginService.login(request);
        }

        throw new LoginFailedException("존재하지 않는 유저입니다.");
    }

    public void logout() {
        logoutService.logout();
    }
}
