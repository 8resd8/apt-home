package com.ssafy.home.info.domain;

import com.ssafy.home.domain.Broker;
import com.ssafy.home.domain.HouseInfo;
import lombok.Getter;

@Getter
public class EstateChart {
    private Long eid;
    private String brokerId;
    private String aptSeq;
    private String type;
    private String status;
    private Integer floor;
    private Integer totalFloor;
    private Double area;
    private String amount;
    private String desc;
    private String createdAt;
    private String updatedAt;
    private String aptNm;

    // 관계 매핑
    private Broker broker;
    private HouseInfo houseInfo;
}
