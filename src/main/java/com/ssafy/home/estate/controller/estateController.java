package com.ssafy.home.estate.controller;

import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.estate.dto.EstateDetailResponseDto;
import com.ssafy.home.estate.dto.RegistEstateRequestDto;
import com.ssafy.home.estate.service.EstateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/estate")
@RestController
public class estateController {

    private final EstateService estateService;

    @PostMapping
    public ResponseEntity<?> postEstate(@Validated @RequestBody RegistEstateRequestDto requestDto) {
        estateService.createBrokerEstate(requestDto, new Broker());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEstate(@PathVariable Long id) {
        EstateDetailResponseDto responseDto = estateService.findEstateDetailById(id);

        return ResponseEntity.ok().body(responseDto);
    }
}