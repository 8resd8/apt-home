package com.ssafy.home.estate.service;

import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.estate.dto.Estate;
import com.ssafy.home.estate.dto.EstateDetailResponse;
import com.ssafy.home.estate.dto.RegistEstateRequest;
import com.ssafy.home.estate.dto.UpdateEstateRequest;

public interface EstateService {
    Long createEstate(RegistEstateRequest requestDto, Broker broker);

    Estate findEstateById(Long id);

    EstateDetailResponse findEstateDetailById(Long id);

    void updateEstate(UpdateEstateRequest requestDto, Broker broker);
}
