package com.ssafy.home.broker.service;

import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.broker.dto.BrokerInfoResponse;

public interface BrokerService {

    public BrokerInfoResponse getBrokerInfo(Broker broker);
}
