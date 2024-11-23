package com.ssafy.home.estate.dto;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class EstateImageResponse {
    private Long imageId;
    private String imageUrl;
    private Timestamp createdAt;
}
