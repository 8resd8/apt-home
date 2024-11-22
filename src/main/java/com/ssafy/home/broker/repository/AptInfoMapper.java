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
            @Param("x1") double x1,
            @Param("y1") double y1,
            @Param("x2") double x2,
            @Param("y2") double y2
    );
}
