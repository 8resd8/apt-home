package com.ssafy.home.profile.member.repository;

import com.ssafy.home.auth.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface MemberProfileMapper {
    Optional<Member> findMemberById(@Param("mid") String memberId);

    int updateMemberProfile(@Param("memberId") String memberId, @Param("password") String password);

    int deleteMemberProfile(@Param("memberId") String memberId, @Param("password") String password);

    int updatePassword(@Param("memberId") String memberId, @Param("newPassword") String requestPassword);
}
