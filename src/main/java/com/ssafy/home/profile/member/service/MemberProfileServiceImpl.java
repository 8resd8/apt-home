package com.ssafy.home.profile.member.service;

import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.auth.exception.LoginFailedException;
import com.ssafy.home.auth.repository.MemberMapper;
import com.ssafy.home.profile.member.dto.MemberResponse;
import com.ssafy.home.profile.member.dto.MemberUpdateRequest;
import com.ssafy.home.profile.member.dto.PasswordChangeRequest;
import com.ssafy.home.profile.member.dto.PasswordResetRequest;
import com.ssafy.home.profile.member.exception.CannotUpdateMemberException;
import com.ssafy.home.profile.member.exception.NotFoundMemberException;
import com.ssafy.home.profile.member.exception.ValidPasswordException;
import com.ssafy.home.profile.member.repository.MemberProfileMapper;
import jakarta.xml.bind.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberProfileServiceImpl implements MemberProfileService {

    private final MemberMapper memberMapper;
    private final MemberProfileMapper memberProfileMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public MemberResponse findMemberById(Member member) {
        Optional<MemberResponse> findMember = memberProfileMapper.findMemberById(member.getMid());

        return getFindMember(findMember);
    }

    @Override
    public MemberResponse updateMember(Member member, MemberUpdateRequest updateRequest) {
        int isSuccess = memberProfileMapper.updateMemberProfile(member.getMid(), updateRequest);
        if (isSuccess == 0) {
            throw new CannotUpdateMemberException();
        }

        Optional<MemberResponse> findMember = memberProfileMapper.findMemberById(member.getMid());

        return getFindMember(findMember);
    }


    @Override
    public void deleteMember(Member member, String requestPassword) {
        Optional<Member> optionalMember = memberMapper.findById(member.getMid());

        if (optionalMember.isEmpty()) {
            throw new NotFoundMemberException();
        }

        Member findMember = optionalMember.get();
        checkPassword(findMember.getPassword(), requestPassword, findMember.getSalt());

        memberProfileMapper.deleteMemberProfile(member.getMid(), requestPassword);
    }

    @Override
    public void resetPassword(PasswordResetRequest passwordResetRequest) {
        // todo: 이메일 인증을 통해 이메일로 비밀번호 재생성 및 DB 반영
    }

    @Override
    public void changePassword(PasswordChangeRequest passwordChangeRequest) {
        // todo 현재비밀번호, 새로운 비밀번호 1 비밀번호 2 일치확인
    }

    private MemberResponse getFindMember(Optional<MemberResponse> findMember) {
        if (findMember.isEmpty()) {
            throw new NotFoundMemberException();
        }

        return findMember.get();
    }

    private void checkPassword(String hashedPassword, String requestPassword, String salt) {
        if (!bCryptPasswordEncoder.matches(requestPassword + salt, hashedPassword)) {
            throw new ValidPasswordException();
        }
    }
}
