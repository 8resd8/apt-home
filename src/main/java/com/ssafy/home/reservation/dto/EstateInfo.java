package com.ssafy.home.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstateInfo {
    private Long eid;
    private String aptSeq;
    private String aptName;
    private String address;
    private String amount;
    private Double area;
    private Integer floor;
    private Integer totalFloor;
    private String description;
    private Double latitude;
    private Double longitude;
    private String estate_image;
}
