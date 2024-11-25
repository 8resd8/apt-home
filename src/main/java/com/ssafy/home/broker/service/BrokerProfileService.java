package com.ssafy.home.broker.service;

import com.ssafy.home.broker.dto.BrokerInfoResponse;

public interface BrokerProfileService {
    BrokerInfoResponse findMemberById(String BrokerId);
}
