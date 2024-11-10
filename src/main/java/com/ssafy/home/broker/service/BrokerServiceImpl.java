package com.ssafy.home.broker.service;

import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.broker.dto.BrokerInfoResponse;
import com.ssafy.home.broker.dto.BrokerUpdateRequest;
import com.ssafy.home.broker.repository.BrokerInfoMapper;
import com.ssafy.home.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class BrokerServiceImpl implements BrokerService {

    private final BrokerInfoMapper brokerInfoMapper;

    @Transactional(readOnly = true)
    @Override
    public BrokerInfoResponse getBrokerInfo(Broker broker) {
        return BrokerInfoResponse.from(broker);
    }

    @Override
    public void updateBroker(Broker broker, BrokerUpdateRequest request) {
        if(brokerInfoMapper.updateBroker(broker.getBid(), request) == 0)
            throw new CustomException(HttpStatus.NOT_FOUND, "업데이트에 실패했습니다.");
    }
}
