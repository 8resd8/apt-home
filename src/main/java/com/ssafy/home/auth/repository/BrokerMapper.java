package com.ssafy.home.auth.repository;

import com.ssafy.home.auth.domain.Broker;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

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
                      @Param("email") String email,
                      @Param("lastLogin") LocalDateTime lastLogin,
                      @Param("createdAt") Timestamp createdAt,
                      @Param("updatedAt") Timestamp updatedAt);

    Optional<Broker> findById(@Param("id") String id);
}
