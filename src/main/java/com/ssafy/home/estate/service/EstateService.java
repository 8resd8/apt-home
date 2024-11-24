package com.ssafy.home.estate.service;

import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.estate.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EstateService {
    Long createEstate(Broker broker, RegistEstateRequest request, MultipartFile[] estateImages);

    Estate findEstateById(Long id);

    EstateDetailResponse findEstateDetailById(Long id);

    EstateDetailResponse findEstateDetailWithMember(Long id, Member member);

    void updateEstate(Broker broker, UpdateEstateRequest request, MultipartFile[] estateImages);

    void deleteEstate(Long eid, Broker broker);

    List<Estate> getEstateListByRegionCode(String sgg, String umd);

    List<Estate> getEstateListByPosition(double latMin, double latMax, double lngMin, double lngMax);

    List<Estate> findAll(Broker broker);

    List<EstateFindResponse> findFavoritesByMemberId(Member member);
}
