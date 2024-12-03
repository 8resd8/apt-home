package com.ssafy.home.estate.controller;

import com.ssafy.home.domain.Broker;
import com.ssafy.home.domain.Estate;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/broker/estate")
@RestController
public class EstateManageController {
    private final EstateService estateService;

    @GetMapping
    public List<Estate> getAllEstates(@Login Broker broker) {
        return estateService.findAll(broker.getBid());
    }

    @PostMapping
    public ResponseEntity<Long> postEstate(@Login Broker broker, @Validated @RequestPart("estate") RegistEstateRequest request,
                                           @RequestPart("estateImages") MultipartFile[] estateImages) {

        System.out.println("....... ㅜㅜㅜㅜㅜㅜ");
        Long createdId = estateService.createEstate(broker, request, estateImages);

        return ResponseEntity.ok().body(createdId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public void updateEstate(@Login Broker broker, @Validated @RequestPart("estate") UpdateEstateRequest request,
                             @RequestPart(value = "estateImages", required = false) MultipartFile[] estateImages) {
        estateService.updateEstate(broker, request, estateImages);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{estateId}")
    public void deleteEstate(@PathVariable Long estateId, @Login Broker broker) {
        estateService.deleteEstate(estateId, broker);
    }
}
