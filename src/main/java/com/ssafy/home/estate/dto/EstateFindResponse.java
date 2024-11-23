package com.ssafy.home.estate.dto;

import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;

@Getter
public class EstateFindResponse {
    private Long eid;
    private String brokerId;
    private String aptSeq;
    private String type;
    private String status;
    private int floor;
    private int totalFloor;
    private double area;
    private String amount;
    private String description;
    private String image;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private List<EstateImageResponse> estateImage; // resultMap과 일치하도록 이름 변경
}
