package com.ssafy.home.auth.service.signup;

import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.auth.dto.MemberSignUpRequest;
import com.ssafy.home.auth.dto.SignUpResponse;
import com.ssafy.home.auth.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSignUpService {

    private final MemberMapper memberMapper;
    private final SignUpHelper signUpHelper;

    public SignUpResponse signUp(MemberSignUpRequest request) {
        signUpHelper.checkDuplicatedId(request.id());

        String salt = signUpHelper.generateSalt();
        String hashedPassword = signUpHelper.hashPassword(request.password(), salt);

        Member member = Member.toEntity(request.id(), hashedPassword, salt, request.email(), request.phoneNum(), request.name());
        memberMapper.insertMember(member);

        return new SignUpResponse(request.id(), request.email());
    }


}
