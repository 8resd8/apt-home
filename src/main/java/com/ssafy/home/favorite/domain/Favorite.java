package com.ssafy.home.favorite.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Builder
@ToString
public class Favorite {
    private Long fid;
    private String memberId;
    private Long estateId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
