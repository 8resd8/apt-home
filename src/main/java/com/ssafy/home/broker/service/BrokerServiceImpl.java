package com.ssafy.home.broker.service;

import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.broker.dto.BrokerInfoResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class BrokerServiceImpl implements BrokerService {

    @Transactional(readOnly = true)
    @Override
    public BrokerInfoResponse getBrokerInfo(Broker broker) {
        return BrokerInfoResponse.from(broker);
    }
}
