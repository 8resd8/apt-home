package com.ssafy.home.estate.controller;

import com.ssafy.home.domain.Member;
import com.ssafy.home.estate.dto.EstateDetailResponse;
import com.ssafy.home.estate.service.EstateService;
import com.ssafy.home.global.annotation.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class EstateMemberController {

    private final EstateService estateService;

    @GetMapping("/estate/{estateId}")
    public ResponseEntity<EstateDetailResponse> getEstateWithMember(@Login Member member, @PathVariable Long estateId) {
        EstateDetailResponse response = estateService.findEstateDetailWithMember(estateId, member);

        return ResponseEntity.ok().body(response);
    }
}
