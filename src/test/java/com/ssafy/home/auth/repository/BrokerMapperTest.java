package com.ssafy.home.auth.repository;

import com.ssafy.home.auth.domain.Broker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
@ActiveProfiles("test") // test설정 쓴다는 의미
class BrokerMapperTest {

    @Autowired
    private BrokerMapper brokerMapper;

    @BeforeEach
    void setUp() {
        for (int i = 1; i <= 30; i++) {
            Timestamp now = Timestamp.valueOf(LocalDateTime.now());
            String bid = "broker" + i;
            Broker broker = Broker.builder()
                    .bid(bid)
                    .officeName("Test Office " + i)
                    .brokerName("John Doe " + i)
                    .phoneNum("010-1234-567" + i)
                    .address("Seoul " + i)
                    .licenseNum("123-45-678" + i)
                    .password("hashed_password_" + i)
                    .salt("random_salt_" + i)
                    .email("broker" + i + "@test.com")
                    .createdAt(now)
                    .updatedAt(now)
                    .lastLogin(LocalDateTime.now())
                    .build();

            brokerMapper.insertBroker(
                    broker.getBid(),
                    broker.getOfficeName(),
                    broker.getBrokerName(),
                    broker.getPhoneNum(),
                    broker.getAddress(),
                    broker.getLicenseNum(),
                    broker.getPassword(),
                    broker.getSalt(),
                    broker.getEmail()
            );
        }
    }


    @Test
    @DisplayName("ID찾기 성공")
    void findById() {
        Optional<Broker> broker = brokerMapper.findById("broker2");

        assertTrue(broker.isPresent());

        Broker broker1 = broker.get();
        Assertions.assertThat(broker1.getBid()).isEqualTo("broker2");
        Assertions.assertThat(broker1.getPassword()).isEqualTo("hashed_password_2");

    }

    @Test
    @DisplayName("ID찾기 실패")
    void findByIdFail() {
        Optional<Broker> broker = brokerMapper.findById("broker0");

        assertFalse(broker.isPresent());
    }
}