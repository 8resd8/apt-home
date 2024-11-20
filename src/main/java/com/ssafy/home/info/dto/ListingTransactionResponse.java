package com.ssafy.home.info.dto;

import java.util.List;

public record ListingTransactionResponse(
        String aptSeq,
        List<ListingTransaction> listingTransactions
) {
}