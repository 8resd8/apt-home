package com.ssafy.home.estate.controller;

import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.estate.dto.RegistEstateRequestDto;
import com.ssafy.home.estate.service.EstateService;
import com.ssafy.home.global.annotation.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/broker/estate")
@RestController
public class estateManageController {
    private final EstateService estateService;

    @PostMapping
    public ResponseEntity<?> postEstate(@Validated @RequestBody RegistEstateRequestDto requestDto, @Login Broker broker) {
        Long createdId = estateService.createEstate(requestDto, broker);

        return ResponseEntity.ok().body(createdId);
    }
}
