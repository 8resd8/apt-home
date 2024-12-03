package com.ssafy.home.domain;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
public class Favorite {
    private Long fid;
    private String memberId;
    private Long estateId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
