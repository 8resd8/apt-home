package com.ssafy.home.broker.repository;

import com.ssafy.home.broker.dto.EstateResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AptInfoMapper {

    // sgg_cd, umd_cd, apt_seq 기반 조회
    List<EstateResponse> findEstateByCodes(
            @Param("sggCd") String sggCd,
            @Param("umdCd") String umdCd
    );

    // 위도, 경도를 기준으로 포함되는 아파트 리스트 조회
    List<EstateResponse> findEstatesByLocation(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
            @Param("radius") double radius // 반경 (예: km 단위)
    );
}
