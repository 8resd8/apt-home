package com.ssafy.home.estate.dto;

import com.ssafy.home.global.enums.EstateType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class RegistEstateRequest {

        @NotNull(message = "{required.field}")
        @Length(max = 20, message = "{size.max}")
        private String aptSeq;

        @NotNull(message = "{required.field}")
        private EstateType type;

        @NotNull(message = "{required.field}")
        @Length(max = 45, message = "{size.max}")
        private String amount;

        @NotNull(message = "{required.field}")
        private Integer floor;

        @NotNull(message = "{required.field}")
        private Integer totalFloor;

        @NotNull(message = "{required.field}")
        private Double area;

        @NotNull(message = "{required.field}")
        private String desc;
}
