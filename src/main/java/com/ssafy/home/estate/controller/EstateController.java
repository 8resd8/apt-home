package com.ssafy.home.estate.controller;

import com.ssafy.home.domain.Estate;
import com.ssafy.home.estate.dto.EstateDetailResponse;
import com.ssafy.home.estate.dto.EstateFindResponse;
import com.ssafy.home.estate.service.EstateFindService;
import com.ssafy.home.estate.service.EstateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/estate")
@RestController
public class EstateController {

    private final EstateService estateService;
    private final EstateFindService estateFindService;


    @GetMapping("/{estateId}")
    public ResponseEntity<EstateDetailResponse> getEstate(@PathVariable Long estateId) {
        EstateDetailResponse response = estateService.findEstateDetailById(estateId);

        return ResponseEntity.ok().body(response);
    }

    // 이미지 목록들 가져오기
    @GetMapping("/images/{estateId}")
    public ResponseEntity<EstateFindResponse> getEstateImages(@PathVariable Long estateId) {
        EstateFindResponse response = estateFindService.findEstateDetailById(estateId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/regions/{sgg}/{umd}")
    public ResponseEntity<List<Estate>> getEstateListByRegionCode(@PathVariable String sgg, @PathVariable String umd) {
        List<Estate> list = estateService.getEstateListByRegionCode(sgg, umd);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping
    public ResponseEntity<List<Estate>> getPropertiesByCoordinates(
            @RequestParam("lat_min") double latMin,
            @RequestParam("lat_max") double latMax,
            @RequestParam("lng_min") double lngMin,
            @RequestParam("lng_max") double lngMax) {

        List<Estate> list = estateService.getEstateListByPosition(latMin, latMax, lngMin, lngMax);

        return ResponseEntity.ok().body(list);
    }
}