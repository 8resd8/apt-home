package com.ssafy.home.estate.dto;

import com.ssafy.home.global.enums.estateType;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record RegistEstateRequestDto(
        @NotNull(message = "{required.field}")
        @Length(max = 20, message = "{size.max}")
        String aptSeq,

        @NotNull(message = "{required.field}")
        estateType type,

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
