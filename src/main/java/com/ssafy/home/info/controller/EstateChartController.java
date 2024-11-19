package com.ssafy.home.info.controller;


import com.ssafy.home.info.dto.TransactionDataResponse;
import com.ssafy.home.info.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estate")
@RequiredArgsConstructor
public class EstateChartController {

    private final TransactionService transactionService;

    @GetMapping("/transaction")
    public ResponseEntity<TransactionDataResponse> getTransactionData(@RequestParam(name = "apt_seq") String aptSeq) {
        TransactionDataResponse response = transactionService.getTransactionData(aptSeq);
        return ResponseEntity.ok(response);
    }

}