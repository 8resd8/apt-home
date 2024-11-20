package com.ssafy.home.estate.service;

import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.auth.service.signup.StorageService;
import com.ssafy.home.estate.dto.Estate;
import com.ssafy.home.estate.dto.EstateDetailResponse;
import com.ssafy.home.estate.dto.UpdateEstateRequest;
import com.ssafy.home.estate.exception.ForbiddenException;
import com.ssafy.home.estate.repository.EstateMapper;
import com.ssafy.home.estate.dto.RegistEstateRequest;
import com.ssafy.home.global.exception.CustomException;
import com.ssafy.home.global.repository.UtilMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional
@Service
public class EstateServiceImpl implements EstateService {

    private final EstateMapper estateMapper;
    private final UtilMapper utilMapper;
    private final StorageService storageService;

    @Override
    public Long createEstate(Broker broker, RegistEstateRequest request, MultipartFile estateImage) {
        String imageUrl = storageService.uploadFile(estateImage);
        estateMapper.insertBrokerEstate(request, broker.getBid(), imageUrl);

        return utilMapper.selectLastInsertId();
    }

    @Transactional(readOnly = true)
    @Override
    public Estate findEstateById(Long id) {
        return estateMapper.selectEstate(id).orElseThrow(() -> new NoSuchElementException("해당 매물을 찾을 수 없습니다."));
    }

    @Transactional(readOnly = true)
    @Override
    public EstateDetailResponse findEstateDetailById(Long id) {
        return estateMapper.selectEstateDetail(id);
    }

    @Override
    public void updateEstate(UpdateEstateRequest requestDto, Broker broker) {
        Estate estate = findEstateById(requestDto.eid());

        if(!estate.getBrokerId().equals(broker.getBid()))
            throw new ForbiddenException();

        if(estateMapper.updateEstate(requestDto) == 0) {
            throw new CustomException(HttpStatus.NOT_FOUND, "업데이트에 실패했습니다.");
        }
    }

    @Override
    public void deleteEstate(Long eid, Broker broker) {
        Estate estate = findEstateById(eid);

        if(!estate.getBrokerId().equals(broker.getBid()))
            throw new ForbiddenException();

        if(estateMapper.deleteEstate(eid) == 0)
            throw new CustomException(HttpStatus.NOT_FOUND, "삭제에 실패했습니다.");
    }

    @Override
    public List<Estate> getEstateListByRegionCode(String sgg, String umd) {
        return estateMapper.getEstateListByRegionCode(sgg, umd);
    }

    @Override
    public List<Estate> getEstateListByPosition(double latMin, double latMax, double lngMin, double lngMax) {
        return estateMapper.getEstateListByPosition(latMin, latMax, lngMin, lngMax);
    }
}