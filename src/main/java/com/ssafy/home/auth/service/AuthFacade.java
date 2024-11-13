package com.ssafy.home.auth.service;

import com.ssafy.home.auth.dto.*;
import com.ssafy.home.auth.exception.LoginFailedException;
import com.ssafy.home.global.enums.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthFacade {

    private final MemberSignUpService memberSignUpService;
    private final BrokerSignUpService brokerSignUpService;
    private final MemberLoginService memberLoginService;
    private final BrokerLoginService brokerLoginService;
    private final LogoutService logoutService;
    private final LoginHelper loginHelper;

    public SignUpResponse signUpMember(MemberSignUpRequest memberRequest) {
        SignUpResponse response = memberSignUpService.signUp(memberRequest);

        return response;
    }

    public SignUpResponse signUpBroker(BrokerSignUpRequest brokerRequest) {
        SignUpResponse response = brokerSignUpService.signUp(brokerRequest);

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
