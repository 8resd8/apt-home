package com.ssafy.home.auth.service.login;

import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.auth.dto.request.LoginRequest;
import com.ssafy.home.auth.dto.response.LoginResponse;
import com.ssafy.home.auth.exception.LoginFailedException;
import com.ssafy.home.auth.repository.BrokerMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ssafy.home.global.enums.UserType.BROKER;

@Service
@RequiredArgsConstructor
public class BrokerLoginService {

    private final BrokerMapper brokerMapper;
    private final LoginHelper loginHelper;
    private final HttpSession session;

    public LoginResponse login(LoginRequest request) {
        Broker broker = brokerMapper.findById(request.id()).orElseThrow(LoginFailedException::new);

        brokerMapper.updateLastLogin(broker.getBid());

        loginHelper.checkPassword(broker.getPassword(), request.password(), broker.getSalt());
        loginHelper.setSessionAttribute(broker.getBid(), BROKER);

        return new LoginResponse(broker.getBid(), broker.getBrokerName(), broker.getEmail(), BROKER.name(), session.getId());
    }

}
