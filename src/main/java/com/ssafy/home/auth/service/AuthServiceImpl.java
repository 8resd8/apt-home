package com.ssafy.home.auth.service;

import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.auth.dto.*;
import com.ssafy.home.auth.exception.DuplicateException;
import com.ssafy.home.auth.exception.LoginFailedException;
import com.ssafy.home.auth.repository.BrokerMapper;
import com.ssafy.home.auth.repository.MemberMapper;
import com.ssafy.home.enums.Session;
import com.ssafy.home.enums.UserType;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberMapper memberMapper;
    private final BrokerMapper brokerMapper;
    private final HttpSession session;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public ResponseSignUp signUpMember(RequestMemberSignUp requestDto) {
        checkDuplicatedId(requestDto.id());

        String salt = generateSalt();
        String hashedPassword = hashPassword(requestDto.password(), salt);
        LocalDateTime now = LocalDateTime.now();

        memberMapper.insertMember(
                requestDto.id(), hashedPassword, salt, requestDto.email(),
                requestDto.phoneNum(), requestDto.name(), now, now);

        return new ResponseSignUp(requestDto.id(), requestDto.email(), now);
    }


    @Override
    public ResponseSignUp signUpBroker(RequestBrokerSignUp requestDto) {
        checkDuplicatedId(requestDto.id());

        String salt = generateSalt();
        String hashedPassword = hashPassword(requestDto.password(), salt);
        LocalDateTime now = LocalDateTime.now();

        brokerMapper.insertBroker(requestDto.id(), requestDto.officeName(), requestDto.name(), requestDto.phoneNum(),
                requestDto.address(), requestDto.licenseNum(), hashedPassword, salt, requestDto.email(), now, now, now);


        return new ResponseSignUp(requestDto.id(), requestDto.email(), now);
    }

    @Override
    public ResponseLoginDto login(RequestLoginDto requestLoginDto) {
        Optional<Member> memberOptional = memberMapper.findById(requestLoginDto.id());
        Optional<Broker> brokerOptional = brokerMapper.findById(requestLoginDto.id());

        if (memberOptional.isPresent()) {
            return MemberLogin(requestLoginDto, memberOptional);
        }

        if (brokerOptional.isPresent()) {
            return BrokerLogin(requestLoginDto, brokerOptional);
        }

        throw new LoginFailedException();
    }

    @Override
    public void logout() {
        session.invalidate();
    }


    private ResponseLoginDto BrokerLogin(RequestLoginDto requestLoginDto, Optional<Broker> brokerOptional) {
        Broker broker = brokerOptional.get();
        checkPassword(broker.getPassword(), requestLoginDto.password(), broker.getSalt());

        session.setAttribute(Session.BROKER_ID.name(), broker.getId());
        session.setAttribute(Session.TYPE.name(), UserType.BROKER.name());

        return new ResponseLoginDto(broker.getId(), broker.getName(), broker.getEmail(), UserType.BROKER.name(), session.getId());
    }

    private ResponseLoginDto MemberLogin(RequestLoginDto requestLoginDto, Optional<Member> memberOptional) {
        Member member = memberOptional.get();
        checkPassword(member.getPassword(), requestLoginDto.password(), member.getSalt());

        session.setAttribute(Session.MEMBER_ID.name(), member.getId());
        session.setAttribute(Session.TYPE.name(), UserType.MEMBER.name());

        return new ResponseLoginDto(member.getId(), member.getName(), member.getEmail(), UserType.MEMBER.name(), session.getId());
    }

    private String hashPassword(String password, String salt) {
        return passwordEncoder.encode(password + salt);
    }

    // 아이디 중복 검사
    private void checkDuplicatedId(String id) {
        if (memberMapper.findById(id).isPresent() || brokerMapper.findById(id).isPresent()) {
            throw new DuplicateException();
        }
    }

    // 비밀번호 검사
    private void checkPassword(String hashedPassword, String requestPassword, String salt) {
        if (!passwordEncoder.matches(requestPassword + salt, hashedPassword)) {
            throw new LoginFailedException();
        }

        if (!passwordEncoder.matches(requestPassword + salt, hashedPassword)) {
            throw new LoginFailedException();
        }
    }

    private String generateSalt() {
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
    }
}