package com.ssafy.home.estate.repository;

import com.ssafy.home.estate.dto.Estate;
import com.ssafy.home.estate.dto.EstateDetailResponseDto;
import com.ssafy.home.estate.dto.RegistEstateRequestDto;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EstateMapper {
    void insertBrokerEstate(@Param("brokerEstate") RegistEstateRequestDto requestDto, @Param("brokerId") String brokerId);

    EstateDetailResponseDto selectEstateDetail(@Param("eid") Long id);

    Estate selectEstate(@Param("id") Long id);
}
