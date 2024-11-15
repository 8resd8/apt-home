package com.ssafy.home.auth.service.login;

import com.ssafy.home.auth.exception.LoginFailedException;
import com.ssafy.home.auth.repository.BrokerMapper;
import com.ssafy.home.auth.repository.MemberMapper;
import com.ssafy.home.global.enums.UserType;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import static com.ssafy.home.global.enums.Session.*;
import static com.ssafy.home.global.enums.UserType.*;

@Component
@RequiredArgsConstructor
public class LoginHelper {

    private final BCryptPasswordEncoder passwordEncoder;
    private final HttpSession session;
    private final MemberMapper memberMapper;
    private final BrokerMapper brokerMapper;

    public void checkPassword(String hashedPassword, String requestPassword, String salt) {
        if (!passwordEncoder.matches(requestPassword + salt, hashedPassword)) {
            throw new LoginFailedException();
        }
    }

    public void setSessionAttribute(String id, UserType userType) {
        if (userType == BROKER) {
            session.setAttribute(BROKER_ID.name(), id);
        } else if (userType == MEMBER) {
            session.setAttribute(MEMBER_ID.name(), id);
        }

        session.setAttribute(TYPE.name(), userType.name());
    }

    public UserType userTypeCheck(String id) {
        if (memberMapper.findById(id).isPresent()) return MEMBER;
        if (brokerMapper.findById(id).isPresent()) return BROKER;

        throw new LoginFailedException("존재하지 않는 유저입니다.");
    }
}
