package com.ssafy.home.estate.service;

import com.ssafy.home.estate.dto.EstateFindResponse;
import com.ssafy.home.estate.repository.EstateSelectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstateFindService {

    private final EstateSelectMapper estateSelectMapper;

    public EstateFindResponse findEstateDetailById(Long estateId) {
        return estateSelectMapper.selectEstateWithImages(estateId);
    }
}
