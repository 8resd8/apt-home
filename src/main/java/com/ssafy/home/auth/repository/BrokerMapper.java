package com.ssafy.home.auth.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

@Mapper
public interface BrokerMapper {

    void insertBroker(@Param("id") String id,
                      @Param("officeName") String officeName,
                      @Param("name") String name,
                      @Param("phoneNum") String phoneNum,
                      @Param("address") String address,
                      @Param("licenseNum") String licenseNum,
                      @Param("password") String password,
                      @Param("salt") String salt,
                      @Param("lastLogin") LocalDateTime lastLogin,
                      @Param("createdAt") LocalDateTime createdAt,
                      @Param("updatedAt") LocalDateTime updatedAt);
}
