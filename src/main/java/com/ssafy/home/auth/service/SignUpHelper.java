package com.ssafy.home.auth.service;

import com.ssafy.home.auth.exception.DuplicateException;
import com.ssafy.home.auth.repository.BrokerMapper;
import com.ssafy.home.auth.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SignUpHelper {

    private final MemberMapper memberMapper;
    private final BrokerMapper brokerMapper;
    private final BCryptPasswordEncoder passwordEncoder;


    public void checkDuplicatedId(String id) {
        if (memberMapper.findById(id).isPresent() || brokerMapper.findById(id).isPresent()) {
            throw new DuplicateException();
        }
    }

    public String hashPassword(String password, String salt) {
        return passwordEncoder.encode(password + salt);
    }

    public String generateSalt() {
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
    }
}
