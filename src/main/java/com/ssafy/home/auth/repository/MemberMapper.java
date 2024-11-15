package com.ssafy.home.auth.repository;

import com.ssafy.home.auth.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface MemberMapper {

    void insertMember(Member member);

    Optional<Member> findById(@Param("id") String id);

    void updateLastLogin(@Param("id") String id);
}
