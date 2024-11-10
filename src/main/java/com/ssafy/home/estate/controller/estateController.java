package com.ssafy.home.estate.controller;

import com.ssafy.home.estate.dto.Estate;
import com.ssafy.home.estate.dto.EstateDetailResponse;
import com.ssafy.home.estate.service.EstateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/estate")
@RestController
public class estateController {

    private final EstateService estateService;

    @GetMapping("/{id}")
    public ResponseEntity<EstateDetailResponse> getEstate(@PathVariable Long id) {
        EstateDetailResponse responseDto = estateService.findEstateDetailById(id);

        return ResponseEntity.ok().body(responseDto);
    }

    @GetMapping("/regions/{sgg}/{umd}")
    public ResponseEntity<List<Estate>> getEstateListByRegionCode(@PathVariable String sgg, @PathVariable String umd) {
        List list = estateService.getEstateListByRegionCode(sgg, umd);

        return ResponseEntity.ok().body(list);
    }
}