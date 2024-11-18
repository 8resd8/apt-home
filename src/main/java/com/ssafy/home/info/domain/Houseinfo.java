package com.ssafy.home.info.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Houseinfo {
    private String aptSeq;
    private String sggCd;
    private String umdCd;
    private String umdNm;
    private String jibun;
    private String roadNmSggCd;
    private String roadNm;
    private String roadNmBonbun;
    private String roadNmBubun;
    private String aptNm;
    private Integer buildYear;
    private String latitude;
    private String longitude;
}
