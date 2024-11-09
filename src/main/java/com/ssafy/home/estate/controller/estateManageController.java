package com.ssafy.home.estate.controller;

import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.estate.dto.RegistEstateRequest;
import com.ssafy.home.estate.dto.UpdateEstateRequest;
import com.ssafy.home.estate.service.EstateService;
import com.ssafy.home.global.annotation.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/broker/estate")
@RestController
public class estateManageController {
    private final EstateService estateService;

    @PostMapping
    public ResponseEntity<?> postEstate(@Validated @RequestBody RegistEstateRequest requestDto, @Login Broker broker) {
        Long createdId = estateService.createEstate(requestDto, broker);

        return ResponseEntity.ok().body(createdId);
    }

    @PutMapping
    public ResponseEntity<?> updateEstate(@Validated @RequestBody UpdateEstateRequest requestDto, @Login Broker broker) {
        estateService.updateEstate(requestDto, broker);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{eid}")
    public ResponseEntity<?> deleteEstate(@PathVariable Long eid, @Login Broker broker) {
        estateService.deleteEstate(eid, broker);

        return ResponseEntity.ok().build();
    }
}
