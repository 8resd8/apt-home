package com.ssafy.home.estate.repository;

import com.ssafy.home.estate.dto.Estate;
import com.ssafy.home.estate.dto.EstateDetailResponse;
import com.ssafy.home.estate.dto.RegistEstateRequest;
import com.ssafy.home.estate.dto.UpdateEstateRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface EstateMapper {
    void insertEstate(@Param("brokerEstate") RegistEstateRequest request,
                      @Param("brokerId") String brokerId);

    void insertEstateImages(@Param("estateId") Long estateId,
                            @Param("imageUrls") List<String> imageUrls);

    void updateEstate(@Param("request") UpdateEstateRequest request);

    EstateDetailResponse selectEstateDetail(@Param("eid") Long id);

    Optional<Estate> selectEstate(@Param("id") Long id);

    int deleteEstate(Long eid);

    List<Estate> getEstateListByRegionCode(@Param("sgg") String sgg, @Param("umd") String umd);

    List<Estate> getEstateListByPosition(@Param("latMin") double latMin, @Param("latMax") double latMax, @Param("lngMin") double lngMin, @Param("lngMax") double lngMax);

    List<Estate> findAll(String bid);

    void deleteEstateImage(@Param("estateId") Long eid);
}
