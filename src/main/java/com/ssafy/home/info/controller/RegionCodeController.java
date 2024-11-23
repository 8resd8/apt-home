package com.ssafy.home.info.controller;

import com.ssafy.home.info.dto.RegionCodeResponse;
import com.ssafy.home.info.service.RegionCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/region-code")
@RestController
public class RegionCodeController {

    private final RegionCodeService regionCodeService;

    @GetMapping("/umd/{region-code}")
    public ResponseEntity<List<RegionCodeResponse>> getUmdList(@PathVariable("region-code") String regionCode) {
        List<RegionCodeResponse> umdList = regionCodeService.getUmdList(regionCode);
        return ResponseEntity.ok(umdList);
    }

    @GetMapping("/sgg/{region-code}")
    public ResponseEntity<List<RegionCodeResponse>> getSggList(@PathVariable("region-code") String regionCode) {
        List<RegionCodeResponse> sggList = regionCodeService.getSggList(regionCode);
        return ResponseEntity.ok(sggList);
    }
}
