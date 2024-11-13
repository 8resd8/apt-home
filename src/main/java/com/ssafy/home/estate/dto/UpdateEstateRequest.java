package com.ssafy.home.estate.dto;

import com.ssafy.home.global.enums.EstateType;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record UpdateEstateRequest(
        @NotNull(message = "{required.field")
        Long eid,

        @NotNull(message = "{required.field}")
        EstateType type,

        @NotNull(message = "{required.field}")
        @Length(max = 45, message = "{size.max}")
        String amount,

        @NotNull(message = "{required.field}")
        Integer floor,

        @NotNull(message = "{required.field}")
        Integer totalFloor,

        @NotNull(message = "{required.field}")
        Double area,

        @NotNull(message = "{required.field}")
        String desc
) {
}