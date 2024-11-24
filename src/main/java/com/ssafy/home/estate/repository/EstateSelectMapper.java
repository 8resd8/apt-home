package com.ssafy.home.estate.repository;

import com.ssafy.home.estate.dto.EstateFindResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EstateSelectMapper {

    EstateFindResponse selectEstateWithImages(@Param("id") Long estateId);
}
