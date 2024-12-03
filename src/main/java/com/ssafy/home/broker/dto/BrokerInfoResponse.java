package com.ssafy.home.broker.dto;

import com.ssafy.home.domain.Broker;
import lombok.Builder;

@Builder
public record BrokerInfoResponse(
        String officeName,
        String brokerName,
        String phoneNum,
        String address,
        String licenseNum,
        String profileImageUrl
) {

    public static BrokerInfoResponse from(Broker broker) {
        return BrokerInfoResponse.builder()
                .officeName(broker.getOfficeName())
                .brokerName(broker.getBrokerName())
                .phoneNum(broker.getPhoneNum())
                .address(broker.getAddress())
                .licenseNum(broker.getLicenseNum())
                .profileImageUrl(broker.getProfileImageUrl())
                .build();
    }
}
