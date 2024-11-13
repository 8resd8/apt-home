package com.ssafy.home.auth.service;

import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.auth.dto.BrokerSignUpRequest;
import com.ssafy.home.auth.dto.SignUpResponse;
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

        Broker broker = Broker.toEntity(
                request.id(),
                request.officeName(),
                request.name(),
                request.phoneNum(),
                request.address(),
                request.licenseNum(),
                hashedPassword,
                salt,
                request.email()
        );

        brokerMapper.insertBroker(broker);

        return new SignUpResponse(request.id(), request.email());
    }

}
