package com.ssafy.home.profile.member.service;

import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.auth.repository.MemberMapper;
import com.ssafy.home.profile.member.dto.MemberResponse;
import com.ssafy.home.profile.member.dto.MemberUpdateRequest;
import com.ssafy.home.profile.member.dto.PasswordChangeRequest;
import com.ssafy.home.profile.member.dto.PasswordResetRequest;
import com.ssafy.home.profile.member.exception.CannotUpdateMemberException;
import com.ssafy.home.profile.member.exception.NotFoundMemberException;
import com.ssafy.home.profile.member.exception.ValidPasswordException;
import com.ssafy.home.profile.member.repository.MemberProfileMapper;
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
    public void changePassword(Member member, PasswordChangeRequest passwordChangeRequest) {
        // 1. 입력한 기존 비밀번호는 저장된 번호와 일치해야 한다.
        checkCurrentPassword(member, passwordChangeRequest);

        // 2. 새 비밀번호는 일치해야한다.
        if (!passwordChangeRequest.newPassword1().equals(passwordChangeRequest.newPassword2())) {
            throw new ValidPasswordException("새로운 비밀번호가 서로 맞지 않습니다.");
        }

        // 3. 비밀번호 변경
        String newSalt = generateSalt();
        int updateCount = memberProfileMapper.updatePassword(member.getMid(), passwordChangeRequest.newPassword1(), newSalt);

        if (updateCount == 0) {
            throw new CannotUpdateMemberException("비밀번호 변경에 실패했습니다.");
        }
    }

    private void checkCurrentPassword(Member member, PasswordChangeRequest passwordChangeRequest) {
        Optional<Member> optionalMember = memberMapper.findById(member.getMid());
        if (optionalMember.isEmpty()) {
            throw new NotFoundMemberException();
        }

        Member findMember = optionalMember.get();
        String originPwd = findMember.getPassword();

        checkPassword(originPwd, passwordChangeRequest.currentPassword(), member.getSalt());
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

    private String generateSalt() {
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
    }
}
