package com.ssafy.home.estate.dto;

import com.ssafy.home.global.enums.EstateType;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class Estate {
    Long eid;
    String brokerId;
    String aptSeq;
    EstateType type;
    String status;
    Integer floor;
    Integer totalFloor;
    Double area;
    String amount;
    String desc;
    Timestamp createdAt;
    Timestamp updatedAt;
    String estateImage;
}
