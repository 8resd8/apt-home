package com.ssafy.home.estate.repository;

import com.ssafy.home.estate.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface EstateMapper {
    void insertEstate(@Param("brokerEstate") RegistEstateRequest request,
                      @Param("brokerId") String brokerId, @Param("image") String image);

    void insertEstateImages(@Param("estateId") Long estateId,
                            @Param("imageUrls") List<String> imageUrls);

    void updateEstate(@Param("request") UpdateEstateRequest request);


    int deleteEstate(Long eid);

    void deleteEstateImage(@Param("estateId") Long eid);


    EstateDetailResponse selectEstateDetail(@Param("eid") Long id);

    EstateDetailResponse selectEstateDetailWithMember(@Param("eid") Long id, @Param("memberId") String memberId);

    Optional<Estate> selectEstate(@Param("id") Long id);

    List<Estate> getEstateListByRegionCode(@Param("sgg") String sgg, @Param("umd") String umd);

    List<Estate> getEstateListByPosition(@Param("latMin") double latMin, @Param("latMax") double latMax, @Param("lngMin") double lngMin, @Param("lngMax") double lngMax);

    List<Estate> findAll(String bid);


    List<EstateFindResponse> findFavorites(String memberId);
}
