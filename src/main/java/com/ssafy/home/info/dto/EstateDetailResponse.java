package com.ssafy.home.info.dto;

import com.ssafy.home.info.domain.HouseinfoChart;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class EstateDetailResponse {

    private Long eid;
    private String brokerId;
    private String aptSeq;
    private Integer floor;
    private Integer totalFloor;
    private Double area;
    private String amount;
    private String desc;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private HouseinfoChart houseinfoChart;
}
