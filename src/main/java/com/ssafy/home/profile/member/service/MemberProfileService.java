package com.ssafy.home.profile.member.service;

import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.profile.member.dto.MemberResponse;
import com.ssafy.home.profile.member.dto.MemberUpdateRequest;
import com.ssafy.home.profile.member.dto.PasswordChangeRequest;
import com.ssafy.home.profile.member.dto.PasswordResetRequest;

public interface MemberProfileService {
    MemberResponse findMemberById(Member memberResponse);

    MemberResponse updateMember(Member memberResponse, MemberUpdateRequest updateRequest);

    void deleteMember(Member memberResponse, String requestPassword);

    void resetPassword(PasswordResetRequest passwordResetRequest);

    void changePassword(Member member, PasswordChangeRequest passwordChangeRequest);


}
