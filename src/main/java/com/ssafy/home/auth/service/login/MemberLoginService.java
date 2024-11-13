package com.ssafy.home.auth.service.login;

import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.auth.dto.LoginRequest;
import com.ssafy.home.auth.dto.LoginResponse;
import com.ssafy.home.auth.exception.LoginFailedException;
import com.ssafy.home.auth.repository.MemberMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ssafy.home.global.enums.UserType.MEMBER;

@Service
@RequiredArgsConstructor
public class MemberLoginService {

    private final MemberMapper memberMapper;
    private final LoginHelper loginHelper;
    private final HttpSession session;

    public LoginResponse login(LoginRequest request) {
        Member member = memberMapper.findById(request.id()).orElseThrow(LoginFailedException::new);
        loginHelper.checkPassword(member.getPassword(), request.password(), member.getSalt());
        loginHelper.setSessionAttribute(request.id(), MEMBER);

        member.updateLastLogin();
        memberMapper.updateLastLogin(member.getMid(), member.getLastLogin());

        return new LoginResponse(member.getMid(), member.getName(), member.getEmail(), MEMBER.name(), session.getId());
    }

}
