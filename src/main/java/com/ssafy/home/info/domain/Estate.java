package com.ssafy.home.info.domain;

import com.ssafy.home.auth.domain.Broker;

import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Estate {
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
    private Houseinfo houseinfo;
}
