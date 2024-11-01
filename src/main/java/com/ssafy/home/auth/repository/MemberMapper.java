package com.ssafy.home.auth.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

@Mapper
public interface MemberMapper {

    void insertMember(@Param("id") String id,
                      @Param("password") String password,
                      @Param("salt") String salt,
                      @Param("email") String email,
                      @Param("phoneNum") String phoneNum,
                      @Param("name") String name,
                      @Param("createdAt") LocalDateTime createdAt,
                      @Param("updatedAt") LocalDateTime updatedAt);

}
