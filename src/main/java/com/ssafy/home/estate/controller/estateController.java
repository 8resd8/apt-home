package com.ssafy.home.estate.controller;

import com.ssafy.home.estate.dto.EstateDetailResponseDto;
import com.ssafy.home.estate.service.EstateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/estate")
@RestController
public class estateController {

    private final EstateService estateService;

    @GetMapping("/{id}")
    public ResponseEntity<EstateDetailResponseDto> getEstate(@PathVariable Long id) {
        EstateDetailResponseDto responseDto = estateService.findEstateDetailById(id);

        return ResponseEntity.ok().body(responseDto);
    }
}