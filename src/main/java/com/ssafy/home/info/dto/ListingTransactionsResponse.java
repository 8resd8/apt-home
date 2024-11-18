package com.ssafy.home.info.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ListingTransactionsResponse {
    private String aptSeq;
    private List<ListingTransaction> listingTransactions;
}
