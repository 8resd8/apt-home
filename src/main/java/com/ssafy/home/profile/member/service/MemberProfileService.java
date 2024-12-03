package com.ssafy.home.profile.member.service;

import com.ssafy.home.domain.Member;
import com.ssafy.home.profile.member.dto.*;
import org.springframework.web.multipart.MultipartFile;

public interface MemberProfileService {
    MemberResponse findMemberById(Member memberResponse);

    MemberResponse updateMember(Member memberResponse, MemberUpdateRequest updateRequest, MultipartFile image);

    void deleteMember(Member memberResponse, MemberDeleteRequest request);

    void changePassword(Member member, PasswordChangeRequest passwordChangeRequest);

    void resetPassword(PasswordResetRequest request);
}
