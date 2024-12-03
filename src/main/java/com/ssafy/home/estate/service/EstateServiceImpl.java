package com.ssafy.home.estate.service;

import com.ssafy.home.domain.Broker;
import com.ssafy.home.domain.Estate;
import com.ssafy.home.domain.Member;
import com.ssafy.home.auth.service.signup.StorageService;
import com.ssafy.home.estate.dto.*;
import com.ssafy.home.estate.exception.ForbiddenException;
import com.ssafy.home.estate.repository.EstateMapper;
import com.ssafy.home.global.exception.CustomException;
import com.ssafy.home.global.repository.UtilMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
    public Long createEstate(Broker broker, RegistEstateRequest request, MultipartFile[] estateImages) {
        List<String> imageUrls = getImageUrls(estateImages);

        estateMapper.insertEstate(request, broker.getBid(), imageUrls.get(0));

        Long estateId = utilMapper.selectLastInsertId();
        estateMapper.insertEstateImages(estateId, imageUrls);
        return estateId;
    }

    @Override
    public void updateEstate(Broker broker, UpdateEstateRequest request, MultipartFile[] estateImages) {
        Estate estate = findEstateById(request.eid());

        if (!estate.getBrokerId().equals(broker.getBid())) {
            throw new ForbiddenException("해당 매물은 다른 브로커에 의해 관리되고 있습니다.");
        }

        estateMapper.updateEstate(request);
        estateMapper.deleteEstateImage(request.eid());
        List<String> imageUrls = getImageUrls(estateImages);
        estateMapper.insertEstateImages(request.eid(), imageUrls);
    }

    private List<String> getImageUrls(MultipartFile[] estateImages) {
        if (estateImages == null || estateImages.length == 0) return null;

        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile estateImage : estateImages) {
            String imageUrl = storageService.uploadFile(estateImage);
            if (imageUrl == null) continue;
            imageUrls.add(imageUrl);
        }
        return imageUrls;
    }

    @Override
    public void deleteEstate(Long eid, Broker broker) {
        Estate estate = findEstateById(eid);

        if (!estate.getBrokerId().equals(broker.getBid())) {
            throw new ForbiddenException("해당 매물은 다른 브로커에 의해 관리되고 있습니다.");
        }

        if (estateMapper.deleteEstate(eid) == 0) {
            throw new CustomException(HttpStatus.NOT_FOUND, "매물 삭제에 실패했습니다.");
        }
    }


    @Transactional(readOnly = true)
    @Override
    public Estate findEstateById(Long id) {
        return estateMapper.selectEstate(id).orElseThrow(() -> new NoSuchElementException("해당 매물을 찾을 수 없습니다."));
    }

    @Transactional(readOnly = true)
    @Override
    public EstateDetailResponse findEstateDetailById(Long id) {
        EstateDetailResponse response = estateMapper.selectEstateDetail(id);
        if (response == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Estate");
        return response;
    }

    @Transactional(readOnly = true)
    @Override
    public EstateDetailResponse findEstateDetailWithMember(Long id, Member member) {
        EstateDetailResponse response = estateMapper.selectEstateDetailWithMember(id, member.getMid());
        if (response == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Estate");
        return response;
    }


    @Transactional(readOnly = true)
    @Override
    public List<Estate> getEstateListByRegionCode(String sgg, String umd) {
        return estateMapper.getEstateListByRegionCode(sgg, umd);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Estate> getEstateListByPosition(double latMin, double latMax, double lngMin, double lngMax) {
        return estateMapper.getEstateListByPosition(latMin, latMax, lngMin, lngMax);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Estate> findAll(String brokerId) {
        return estateMapper.findAll(brokerId);
    }

    // 찜 목록에서 쓰는 것
    @Override
    public List<EstateFindResponse> findFavoritesByMemberId(Member member) {
        List<EstateFindResponse> favorites = estateMapper.findFavorites(member.getMid());

        return favorites;
    }
}
