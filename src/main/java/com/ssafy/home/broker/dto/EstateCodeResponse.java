package com.ssafy.home.broker.dto;

import java.util.List;

public record EstateCodeResponse(
        List<EstateResponse> estateCodes
) {}
