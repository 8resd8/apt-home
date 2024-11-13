package com.ssafy.home.review.repository;

import com.ssafy.home.review.domain.HouseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HouseInfoMapper {
    HouseInfo findHouseInfoById(@Param("rid") Long rid);
}
