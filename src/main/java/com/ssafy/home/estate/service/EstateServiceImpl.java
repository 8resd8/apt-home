package com.ssafy.home.estate.service;

import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.estate.dto.Estate;
import com.ssafy.home.estate.dto.EstateDetailResponseDto;
import com.ssafy.home.estate.repository.EstateMapper;
import com.ssafy.home.estate.dto.RegistEstateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class EstateServiceImpl implements EstateService {

    private final EstateMapper estateMapper;

    @Override
    public void createBrokerEstate(RegistEstateRequestDto requestDto, Broker broker) {
        estateMapper.insertBrokerEstate(requestDto, broker.getBid());
    }

    @Override
    public Estate findEstateById(Long id) {
        return estateMapper.selectEstate(id);
    }

    @Override
    public EstateDetailResponseDto findEstateDetailById(Long id) {
        return estateMapper.selectEstateDetail(id);
    }
}
