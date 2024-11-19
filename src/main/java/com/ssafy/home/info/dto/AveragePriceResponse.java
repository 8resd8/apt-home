package com.ssafy.home.info.dto;

import java.util.List;


public record AveragePriceResponse(
        String region,
        List<AveragePrice> averagePrices
) {
}