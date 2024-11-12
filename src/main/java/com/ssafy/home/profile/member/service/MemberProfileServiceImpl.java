package com.ssafy.home.profile.member.service;

import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.profile.member.dto.MemberProfileResponse;
import com.ssafy.home.profile.member.dto.MemberUpdateRequest;
import com.ssafy.home.profile.member.dto.PasswordChangeRequest;
import com.ssafy.home.profile.member.dto.PasswordResetRequest;
import com.ssafy.home.profile.member.repository.MemberProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberProfileServiceImpl implements MemberProfileService {

    private final MemberProfileMapper memberProfileMapper;

    @Override
    public MemberProfileResponse findMemberById(String memberId) {
        return null;
    }

    @Override
    public int updateMember(String memberId, MemberUpdateRequest updateRequest) {
        return 0;
    }

    @Override
    public int deleteMember(String memberId, String requestPassword) {
        return 0;
    }

    @Override
    public void resetPassword(PasswordResetRequest passwordResetRequest) {

    }

    @Override
    public void changePassword(PasswordChangeRequest passwordChangeRequest) {

    }
}
