package com.ssafy.home.auth.service.signup;

import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.auth.dto.request.BrokerSignUpRequest;
import com.ssafy.home.auth.dto.response.SignUpResponse;
import com.ssafy.home.auth.repository.BrokerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrokerSignUpService {

    private final BrokerMapper brokerMapper;
    private final SignUpHelper signUpHelper;


    public SignUpResponse signUp(BrokerSignUpRequest request) {
        signUpHelper.checkDuplicatedId(request.id());

        String salt = signUpHelper.generateSalt();
        String hashedPassword = signUpHelper.hashPassword(request.password(), salt);

        Broker broker = Broker.builder()
                .bid(request.id())
                .password(hashedPassword)
                .salt(salt)
                .email(request.email())
                .phoneNum(request.phoneNum())
                .address(request.address())
                .licenseNum(request.licenseNum())
                .brokerName(request.name()).build();

        brokerMapper.insertBroker(broker);

        return new SignUpResponse(request.id(), request.email());
    }

}
