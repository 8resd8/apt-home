package com.ssafy.home.broker.controller;

import com.ssafy.home.broker.dto.BrokerInfoResponse;
import com.ssafy.home.broker.service.BrokerProfileService;
import com.ssafy.home.domain.Estate;
import com.ssafy.home.estate.service.EstateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/office")
@RequiredArgsConstructor
@RestController
public class OpenBrokerController {

    private final BrokerProfileService brokerProfileService;
    private final EstateService estateService;

    @GetMapping("/{brokerId}")
    public ResponseEntity<BrokerInfoResponse> getBroker(@PathVariable String brokerId) {

        BrokerInfoResponse responseDto = brokerProfileService.findMemberById(brokerId);

        return ResponseEntity.ok().body(responseDto);
    }

    @GetMapping("/{brokerId}/estate")
    public ResponseEntity<List<Estate>> getEstateList(@PathVariable String brokerId) {
        return ResponseEntity.ok().body(estateService.findAll(brokerId));
    }
}
