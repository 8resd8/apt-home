package com.ssafy.home.auth.service;

import com.ssafy.home.auth.dto.RequestBrokerSignUp;

import com.ssafy.home.auth.dto.RequestMemberSignUp;
import com.ssafy.home.auth.dto.ResponseSignUp;
import com.ssafy.home.auth.repository.BrokerMapper;
import com.ssafy.home.auth.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberMapper memberMapper;
    private final BrokerMapper brokerMapper;


    @Override
    public ResponseSignUp signUpMember(RequestMemberSignUp requestDto) {
        String password = requestDto.password();
        String salt = generateSalt();
        LocalDateTime now = LocalDateTime.now();

        memberMapper.insertMember(
                requestDto.id(), password, salt, requestDto.email(),
                requestDto.phoneNum(), requestDto.name(), now, now);


        return new ResponseSignUp(requestDto.id(), requestDto.email(), now);
    }

    @Override
    public ResponseSignUp signUpBroker(RequestBrokerSignUp requestDto) {
        String password = requestDto.password();
        String salt = generateSalt();
        LocalDateTime now = LocalDateTime.now();

        brokerMapper.insertBroker(requestDto.id(), requestDto.officeName(), requestDto.name(), requestDto.phoneNum(),
                requestDto.address(), requestDto.licenseNum(), password, salt, now, now, now);


        return new ResponseSignUp(requestDto.id(), requestDto.email(), now);
    }

    private String generateSalt() {
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
    }
}