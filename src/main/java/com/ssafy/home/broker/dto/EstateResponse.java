package com.ssafy.home.broker.dto;

public record EstateResponse(
        String sptSeq,
        String sggName,
        String umdName,
        String umdNm,
        String jibun,
        String roadNmSggCd,
        String roadNm,
        String roadNmBonbun,
        String roadNmBubun,
        String aptNm,
        int buildYear,
        Double latitude,
        Double longitude,
        String pullName
) {}