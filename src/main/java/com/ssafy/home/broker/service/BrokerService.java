package com.ssafy.home.broker.service;

import com.ssafy.home.domain.Broker;
import com.ssafy.home.broker.dto.BrokerInfoResponse;
import com.ssafy.home.broker.dto.BrokerUpdateRequest;

public interface BrokerService {

    BrokerInfoResponse getBrokerInfo(Broker broker);

    void updateBroker(Broker broker, BrokerUpdateRequest request);
}
