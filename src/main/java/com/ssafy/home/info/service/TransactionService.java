package com.ssafy.home.info.service;

import com.ssafy.home.info.dto.*;
import com.ssafy.home.info.repository.TransactionChartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionChartMapper transactionChartMapper;
    private final EstateService estateService;

    public TransactionDataResponse getTransactionData(String aptSeq) {
        // 매물 상세 정보 조회
        EstateDetailResponse estateDetail = estateService.getEstateDetails(aptSeq);
        if (estateDetail == null) {
            throw new NoSuchElementException("매물을 찾을 수 없습니다. apt_seq: " + aptSeq);
        }

        // sggCd 추출
        String sggCd = estateDetail.getHouseinfo().getSggCd();
        System.out.println("시군구: " + sggCd);
        if (sggCd == null || sggCd.isEmpty()) {
            throw new NoSuchElementException("매물이 속한 구 코드를 찾을 수 없습니다. apt_seq: " + aptSeq);
        }

        // 지역별 평균 거래 가격 조회
        List<AveragePrice> averagePrices = transactionChartMapper.getAveragePrices(sggCd);
        if (averagePrices.isEmpty()) {
            throw new NoSuchElementException("구 평균 거래 가격 데이터를 찾을 수 없습니다. sggCd: " + sggCd);
        }

        // 선택 매물의 거래 내역 조회
        List<ListingTransaction> listingTransactions = transactionChartMapper.getListingTransactions(aptSeq);
        if (listingTransactions.isEmpty()) {
            throw new NoSuchElementException("매물 거래 내역을 찾을 수 없습니다. apt_seq: " + aptSeq);
        }

        // 응답 데이터 구성
        return new TransactionDataResponse(listingTransactions, averagePrices);
    }
}
