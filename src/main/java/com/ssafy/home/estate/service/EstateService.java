package com.ssafy.home.estate.service;

import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.estate.dto.Estate;
import com.ssafy.home.estate.dto.EstateDetailResponseDto;
import com.ssafy.home.estate.dto.RegistEstateRequestDto;

public interface EstateService {
    Long createEstate(RegistEstateRequestDto requestDto, Broker broker);

    Estate findEstateById(Long id);

    EstateDetailResponseDto findEstateDetailById(Long id);
}
