package com.ssafy.home.broker.dto;

import java.util.List;

public record EstateListResponse(
        List<EstateResponse> estates
) {}
