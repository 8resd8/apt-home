package com.ssafy.home.info.domain;

import lombok.*;

@Getter
@ToString
public class HouseDeal {
    private Integer no;
    private String aptSeq;
    private String aptDong;
    private String floor;
    private Integer dealYear;
    private Integer dealMonth;
    private Integer dealDay;
    private Double excluUseAr;
    private String dealAmount;
}
