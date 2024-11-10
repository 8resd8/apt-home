package com.ssafy.home.broker.controller;

import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.broker.dto.BrokerInfoResponse;
import com.ssafy.home.broker.dto.BrokerUpdateRequest;
import com.ssafy.home.broker.service.BrokerService;
import com.ssafy.home.global.annotation.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/broker")
@RestController
public class BrokerController {

    private final BrokerService brokerService;

    @GetMapping
    public ResponseEntity<BrokerInfoResponse> getBroker(@Login Broker broker) {

        BrokerInfoResponse responseDto = brokerService.getBrokerInfo(broker);

        return ResponseEntity.ok().body(responseDto);
    }

    @PutMapping
    public ResponseEntity<?> updateBroker(@Login Broker broker, @RequestBody BrokerUpdateRequest requestDto) {
        brokerService.updateBroker(broker, requestDto);

        return ResponseEntity.ok().build();
    }
}