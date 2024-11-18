package com.ssafy.home.info.dto;

import com.ssafy.home.info.domain.Houseinfo;

public record EstateDetailResponse(
        Long eid,
        String brokerId,
        String aptSeq,
        String type,
        String status,
        Integer floor,
        Integer totalFloor,
        Double area,
        String amount,
        String desc,
        String createdAt,
        String updatedAt,
        String aptNm,
        Houseinfo houseinfo
) {
}
