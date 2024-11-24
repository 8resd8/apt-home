package com.ssafy.home.broker.controller;

import com.ssafy.home.broker.dto.BrokerInfoResponse;
import com.ssafy.home.broker.service.BrokerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OpenBrokerController {

    private final BrokerProfileService brokerProfileService;

    @GetMapping("/office/{brokerId}")
    public ResponseEntity<BrokerInfoResponse> getBroker(@PathVariable String brokerId) {

        BrokerInfoResponse responseDto = brokerProfileService.findMemberById(brokerId);

        return ResponseEntity.ok().body(responseDto);
    }
}
