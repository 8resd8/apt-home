package com.ssafy.home.profile.member.service;

import com.ssafy.home.profile.member.dto.MemberProfileResponse;
import com.ssafy.home.profile.member.dto.MemberUpdateRequest;
import com.ssafy.home.profile.member.dto.PasswordChangeRequest;
import com.ssafy.home.profile.member.dto.PasswordResetRequest;

public interface MemberProfileService {
    MemberProfileResponse findMemberById(String memberId);

    int updateMember(String memberId, MemberUpdateRequest updateRequest);

    int deleteMember(String memberId, String requestPassword);

    void resetPassword(PasswordResetRequest passwordResetRequest);

    void changePassword(PasswordChangeRequest passwordChangeRequest);


}
