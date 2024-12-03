package com.ssafy.home.broker.service;

import com.ssafy.home.domain.Broker;
import com.ssafy.home.auth.repository.BrokerMapper;
import com.ssafy.home.broker.dto.BrokerInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class BrokerProfileServiceImpl implements BrokerProfileService {

    private final BrokerMapper brokerMapper;

    @Transactional(readOnly = true)
    @Override
    public BrokerInfoResponse findMemberById(String brokerId) {
        Broker broker = brokerMapper.findById(brokerId).orElseThrow(
                () -> new NoSuchElementException("찾을 수 없는 공인중개사입니다.")
        );

        return BrokerInfoResponse.from(broker);
    }
}
