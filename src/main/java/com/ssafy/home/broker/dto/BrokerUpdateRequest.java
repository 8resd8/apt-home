package com.ssafy.home.broker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record BrokerUpdateRequest(
        @NotBlank(message = "{required.field")
        String name,

        @NotBlank(message = "{required.field")
        String officeName,

        @NotBlank(message = "{required.field")
        String brokerName,

        @NotBlank(message = "{required.field")
        @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "{pattern.phoneNum}")
        String phoneNum,

        @NotBlank(message = "{required.field")
        String address,

        @NotBlank(message = "{required.field")
        String licenseNum
) {
}