package com.ssafy.home.estate.repository;

import com.ssafy.home.estate.dto.Estate;
import com.ssafy.home.estate.dto.EstateDetailResponse;
import com.ssafy.home.estate.dto.RegistEstateRequest;
import com.ssafy.home.estate.dto.UpdateEstateRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface EstateMapper {
    void insertBrokerEstate(@Param("brokerEstate") RegistEstateRequest request, @Param("brokerId") String brokerId,
                            @Param("estateImage") String estateImage);

    EstateDetailResponse selectEstateDetail(@Param("eid") Long id);

    Optional<Estate> selectEstate(@Param("id") Long id);

    int updateEstate(UpdateEstateRequest requestDto);

    int deleteEstate(Long eid);


    List<Estate> getEstateListByRegionCode(@Param("sgg") String sgg, @Param("umd") String umd);

    List<Estate> getEstateListByPosition(@Param("latMin") double latMin, @Param("latMax") double latMax, @Param("lngMin") double lngMin, @Param("lngMax") double lngMax);
}
