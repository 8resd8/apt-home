package com.ssafy.home.info.service;

import com.ssafy.home.info.dto.EstateDetailResponse;
import com.ssafy.home.info.repository.EstateChartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstateService {

    private final EstateChartMapper estateChartMapper;

    public EstateDetailResponse getEstateDetails(String aptSeq) {
        return estateChartMapper.getEstateDetails(aptSeq);
    }
}
