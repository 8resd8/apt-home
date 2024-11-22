package com.ssafy.home.broker.controller;

import com.ssafy.home.broker.dto.EstateListResponse;
import com.ssafy.home.broker.dto.EstateResponse;
import com.ssafy.home.broker.service.AptInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/broker/estate/info")
@RestController
public class AptInfoController {

    private final AptInfoService aptInfoService;

    @GetMapping("/code")
    public ResponseEntity<List<EstateResponse>> getEstateByCodes(
            @RequestParam("sgg_cd") String sggCd,
            @RequestParam("umd_cd") String umdCd
    ) {
        return ResponseEntity.ok(aptInfoService.getEstateByCodes(sggCd, umdCd));
    }

    /**
     * 예시: GET /broker/estate/infos?latitude=37.5665&longitude=126.9780&radius=5
     */
    @GetMapping("/location")
    public ResponseEntity<EstateListResponse> getEstatesByLocation(
            @RequestParam("lat_min") double x1,
            @RequestParam("lat_max") double y1,
            @RequestParam("lng_min") double x2,
            @RequestParam("lng_max") double y2
    ) {
        return ResponseEntity.ok(aptInfoService.getEstatesByLocation(x1, y1, x2, y2));
    }
    // EC2
}
