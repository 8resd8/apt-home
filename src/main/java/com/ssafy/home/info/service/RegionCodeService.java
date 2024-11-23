package com.ssafy.home.info.service;

import com.ssafy.home.info.dto.RegionCodeResponse;
import com.ssafy.home.info.repository.RegionCodeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RegionCodeService {

    private final RegionCodeMapper regionCodeMapper;

    public List<RegionCodeResponse> getSggList(String regionCode) {
        String pattern = regionCode.substring(0, 2).concat("%00000");
        return regionCodeMapper.getSggList(pattern);
    }

    public List<RegionCodeResponse> getUmdList(String regionCode) {
        String pattern = regionCode.substring(0, 5).concat("%");
        return regionCodeMapper.getUmdList(pattern);
    }
}
