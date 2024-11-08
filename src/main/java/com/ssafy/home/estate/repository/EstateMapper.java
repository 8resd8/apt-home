package com.ssafy.home.estate.repository;

import com.ssafy.home.estate.dto.Estate;
import com.ssafy.home.estate.dto.EstateDetailResponse;
import com.ssafy.home.estate.dto.RegistEstateRequest;
import com.ssafy.home.estate.dto.UpdateEstateRequest;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EstateMapper {
    void insertBrokerEstate(@Param("brokerEstate") RegistEstateRequest requestDto, @Param("brokerId") String brokerId);

    EstateDetailResponse selectEstateDetail(@Param("eid") Long id);


    Estate selectEstate(@Param("id") Long id);
}
