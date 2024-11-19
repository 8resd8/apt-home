package com.ssafy.home.info.dto;

import java.util.List;

public record TransactionDataResponse(
        List<ListingTransaction> estateDeals,
        List<AveragePrice> averageDeals
) {}