package com.ssafy.home.info.repository;

import com.ssafy.home.info.dto.EstateDetailResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EstateChartMapper {
    EstateDetailResponse getEstateDetails(@Param("aptSeq") String aptSeq);
}
