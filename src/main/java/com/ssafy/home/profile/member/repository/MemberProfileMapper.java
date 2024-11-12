package com.ssafy.home.profile.member.repository;


import com.ssafy.home.profile.member.dto.MemberResponse;
import com.ssafy.home.profile.member.dto.MemberUpdateRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface MemberProfileMapper {
    Optional<MemberResponse> findMemberById(@Param("memberId") String memberId);

    int updateMemberProfile(@Param("memberId") String memberId, @Param("update") MemberUpdateRequest updateRequest);

    int deleteMemberProfile(@Param("memberId") String memberId, @Param("password") String password);

    int updatePassword(@Param("memberId") String memberId, @Param("newPassword") String requestPassword);
}
