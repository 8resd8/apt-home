package com.ssafy.home.estate.dto;

public record EstateDetailResponse(
        //매물 정보
        Long eid,
        String type,
        String status,
        Integer floor,
        Integer totalFloor,
        Double area,
        String amount,
        String desc,

        //부동산 정보
        String brokerId,
        String officeName,
        String phoneNum,
        String address,
        String licenseNum,

        //아파트 정보
        String aptNm,
        String buildYear,
        String roadNm,
        String roadNmNo
) {
}
