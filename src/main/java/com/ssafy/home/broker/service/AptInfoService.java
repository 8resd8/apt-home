package com.ssafy.home.broker.service;

import com.ssafy.home.broker.dto.EstateListResponse;
import com.ssafy.home.broker.dto.EstateResponse;
import com.ssafy.home.broker.repository.AptInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AptInfoService {

    private final AptInfoMapper aptInfoMapper;


    // sgg_cd, umd_cd, apt_seq 기반 조회
    public List<EstateResponse> getEstateByCodes(String sggCd, String umdCd) {
        List<EstateResponse> findEstate = aptInfoMapper.findEstateByCodes(sggCd, umdCd);
        if (findEstate == null) throw new NoSuchElementException("Estate 찾기 실패");

        return findEstate;
    }

    // 위도, 경도 및 반경 기반 아파트 리스트 조회
    public EstateListResponse getEstatesByLocation(double latitude, double longitude, double radius) {
        List<EstateResponse> estates = aptInfoMapper.findEstatesByLocation(latitude, longitude, radius);
        if (estates == null) throw new NoSuchElementException("EstateList 찾기 실패");

        return new EstateListResponse(estates);
    }
}
