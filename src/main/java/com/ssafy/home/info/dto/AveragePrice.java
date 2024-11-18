package com.ssafy.home.info.dto;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AveragePrice {
    private String month;
    private Long averageAmount;
}
