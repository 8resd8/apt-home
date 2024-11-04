package com.ssafy.home.estate.dto;

import com.ssafy.home.global.enums.estateType;
import lombok.Getter;

@Getter
public class Estate {
    Long eid;
    estateType type;
    String status;
    Integer floor;
    Integer totalFloor;
    Double area;
    String amount;
    String desc;
}
