package com.ssafy.home.auth.repository;

import com.ssafy.home.auth.domain.Broker;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface BrokerMapper {

    void insertBroker(Broker broker);

    Optional<Broker> findById(@Param("id") String id);

    void updateLastLogin(@Param("id") String id);
}
