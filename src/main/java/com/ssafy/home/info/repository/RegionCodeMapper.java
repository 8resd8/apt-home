package com.ssafy.home.info.repository;

import com.ssafy.home.info.dto.RegionCodeResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegionCodeMapper {

    List<RegionCodeResponse> getUmdList(String pattern);

    List<RegionCodeResponse> getSggList(String pattern);
}
