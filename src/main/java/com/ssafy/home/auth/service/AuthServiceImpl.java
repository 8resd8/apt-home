package com.ssafy.home.auth.service;

import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.auth.dto.*;
import com.ssafy.home.auth.exception.DeleteAccountFailedException;
import com.ssafy.home.auth.exception.DuplicatedException;
import com.ssafy.home.auth.exception.LoginFailedException;
import com.ssafy.home.auth.repository.BrokerMapper;
import com.ssafy.home.auth.repository.MemberMapper;
import com.ssafy.home.enums.Session;
import com.ssafy.home.enums.UserType;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final MemberMapper memberMapper;
    private final BrokerMapper brokerMapper;
    private final HttpSession session;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public SignUpResponse signUpMember(MemberSignUpRequest requestDto) {
        checkDuplicatedId(requestDto.id());

        String salt = generateSalt();
        String hashedPassword = hashPassword(requestDto.password(), salt);
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());

        memberMapper.insertMember(
                requestDto.id(), hashedPassword, salt, requestDto.email(),
                requestDto.phoneNum(), requestDto.name());

        return new SignUpResponse(requestDto.id(), requestDto.email(), now);
    }


    @Override
    public SignUpResponse signUpBroker(BrokerSignUpRequest requestDto) {
        checkDuplicatedId(requestDto.id());

        String salt = generateSalt();
        String hashedPassword = hashPassword(requestDto.password(), salt);
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());

        brokerMapper.insertBroker(requestDto.id(), requestDto.officeName(), requestDto.name(), requestDto.phoneNum(),
                requestDto.address(), requestDto.licenseNum(), hashedPassword, salt, requestDto.email());


        return new SignUpResponse(requestDto.id(), requestDto.email(), now);
    }

    @Override
    public LoginDtoResponse login(LoginRequest loginRequest) {
        Optional<Member> memberOptional = memberMapper.findById(loginRequest.id());
        Optional<Broker> brokerOptional = brokerMapper.findById(loginRequest.id());

        if (memberOptional.isPresent()) {
            return MemberLogin(loginRequest, memberOptional);
        }

        if (brokerOptional.isPresent()) {
            return BrokerLogin(loginRequest, brokerOptional);
        }

        throw new LoginFailedException();
    }

    @Override
    public void logout() {
        session.invalidate();
    }

    @Override
    public boolean deleteAccount(String userId, String userType) {
        if (userType.equals(UserType.MEMBER.name())) {
            deleteMember(userId);
            return true;
        }

        if (userType.equals(UserType.BROKER.name())) {
            deleteBroker(userId);
            return true;
        }

        throw new DeleteAccountFailedException("잘못된 회원 유형입니다.");
    }

    private void deleteBroker(String userId) {
        if (brokerMapper.deleteById(userId) == 0) {
            throw new DeleteAccountFailedException("해당 중개사가 존재하지 않습니다.");
        }
    }

    private void deleteMember(String userId) {
        if (memberMapper.deleteById(userId) == 0) {
            throw new DeleteAccountFailedException("해당 회원이 존재하지 않습니다.");
        }
    }


    private LoginDtoResponse BrokerLogin(LoginRequest loginRequest, Optional<Broker> brokerOptional) {
        Broker broker = brokerOptional.get();
        checkPassword(broker.getPassword(), loginRequest.password(), broker.getSalt());

        brokerMapper.updateLastLogin(broker.getBid());

        session.setAttribute(Session.BROKER_ID.name(), broker.getBid());
        session.setAttribute(Session.TYPE.name(), UserType.BROKER.name());

        return new LoginDtoResponse(broker.getBid(), broker.getBrokerName(), broker.getEmail(), UserType.BROKER.name(), session.getId());
    }

    private LoginDtoResponse MemberLogin(LoginRequest loginRequest, Optional<Member> memberOptional) {
        Member member = memberOptional.get();
        checkPassword(member.getPassword(), loginRequest.password(), member.getSalt());

        memberMapper.updateLastLogin(member.getMid());

        session.setAttribute(Session.MEMBER_ID.name(), member.getMid());
        session.setAttribute(Session.TYPE.name(), UserType.MEMBER.name());

        return new LoginDtoResponse(member.getMid(), member.getName(), member.getEmail(), UserType.MEMBER.name(), session.getId());
    }

    private String hashPassword(String password, String salt) {
        return passwordEncoder.encode(password + salt);
    }

    // 아이디 중복 검사
    private void checkDuplicatedId(String id) {
        if (memberMapper.findById(id).isPresent() || brokerMapper.findById(id).isPresent()) {
            throw new DuplicatedException();
        }
    }

    // 비밀번호 검사
    private void checkPassword(String hashedPassword, String requestPassword, String salt) {
        if (!passwordEncoder.matches(requestPassword + salt, hashedPassword)) {
            throw new LoginFailedException();
        }
    }

    private String generateSalt() {
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
    }
}