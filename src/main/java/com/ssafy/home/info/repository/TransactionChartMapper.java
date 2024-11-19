package com.ssafy.home.info.repository;

import com.ssafy.home.info.dto.AveragePrice;
import com.ssafy.home.info.dto.ListingTransaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TransactionChartMapper {
    List<AveragePrice> getAveragePrices(@Param("sggCd") String sggCd);

    List<ListingTransaction> getListingTransactions(@Param("aptSeq") String aptSeq);
}
