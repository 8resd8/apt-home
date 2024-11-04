package com.ssafy.home.estate.service;

import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.estate.dto.RegistEstateRequestDto;

public interface EstateService {
    void createBrokerEstate(RegistEstateRequestDto requestDto, Broker broker);
}
