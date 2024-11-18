package com.ssafy.home.info.dto;

public record AveragePricesResponse(
        String region,
        List<AveragePrice> averagePrices
) {
}
