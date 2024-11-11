package com.ssafy.home.estate.controller;

import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.estate.dto.RegistEstateRequest;
import com.ssafy.home.estate.dto.UpdateEstateRequest;
import com.ssafy.home.estate.service.EstateService;
import com.ssafy.home.global.annotation.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/broker/estate")
@RestController
public class EstateManageController {
    private final EstateService estateService;

    @PostMapping
    public ResponseEntity<Long> postEstate(@Validated @RequestBody RegistEstateRequest requestDto, @Login Broker broker) {
        Long createdId = estateService.createEstate(requestDto, broker);

        return ResponseEntity.ok().body(createdId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public void updateEstate(@Validated @RequestBody UpdateEstateRequest requestDto, @Login Broker broker) {
        estateService.updateEstate(requestDto, broker);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{eid}")
    public void deleteEstate(@PathVariable Long eid, @Login Broker broker) {
        estateService.deleteEstate(eid, broker);
    }
}
