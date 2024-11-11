package com.ssafy.home.estate.dto;

import com.ssafy.home.global.enums.EstateType;
import lombok.Getter;

@Getter
public class Estate {
    Long eid;
    String brokerId;
    EstateType type;
    String status;
    Integer floor;
    Integer totalFloor;
    Double area;
    String amount;
    String desc;
}
