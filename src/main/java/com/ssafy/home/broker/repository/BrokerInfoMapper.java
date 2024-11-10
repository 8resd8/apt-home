package com.ssafy.home.broker.repository;

import com.ssafy.home.broker.dto.BrokerUpdateRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BrokerInfoMapper {

    int updateBroker(@Param("bid") String bid, @Param("requestDto") BrokerUpdateRequest requestDto);
}
