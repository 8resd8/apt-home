package com.ssafy.home.auth.repository;

import com.ssafy.home.auth.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    void insertMember(@Param("id") String id,
                      @Param("password") String password,
                      @Param("salt") String salt,
                      @Param("email") String email,
                      @Param("phoneNum") String phoneNum,
                      @Param("name") String name);

    Optional<Member> findById(@Param("id") String id);

    int deleteById(@Param("id") String userId);

    void updateLastLogin(@Param("id") String id);
}
