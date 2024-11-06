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

import static org.assertj.core.api.Assertions.*;
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
        assertThat(broker1.getBid()).isEqualTo("broker2");
        assertThat(broker1.getPassword()).isEqualTo("hashed_password_2");

    }

    @Test
    @DisplayName("ID찾기 실패")
    void findByIdFail() {
        Optional<Broker> broker = brokerMapper.findById("broker0");

        assertFalse(broker.isPresent());
    }

    @Test
    @DisplayName("이메일로 Broker 조회 성공")
    void findByEmail_success() {
        Optional<Object> findBroker = brokerMapper.findByEmail("broker1@test.com");

        assertThat(findBroker).isPresent();
        Broker broker = (Broker) findBroker.get();
        assertThat(broker.getEmail()).isEqualTo("broker1@test.com");
    }

    @Test
    @DisplayName("존재하지 않는 이메일로 Broker 조회 실패")
    void findByEmail_fail() {
        Optional<Object> foundBroker = brokerMapper.findByEmail("nonexistent@test.com");

        assertThat(foundBroker).isNotPresent();
    }

    @Test
    @DisplayName("ID로 Broker 삭제 성공")
    void deleteById_success() {
        String brokerId = "broker1";

        int result = brokerMapper.deleteById(brokerId);

        assertThat(result).isEqualTo(1);
        assertThat(brokerMapper.findById(brokerId)).isNotPresent();
    }

    @Test
    @DisplayName("존재하지 않는 ID로 Broker 삭제 실패")
    void deleteById_fail() {
        String nonExistentId = "nonexistentBroker";

        int result = brokerMapper.deleteById(nonExistentId);

        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("마지막 로그인 시간 업데이트 성공")
    void updateLastLogin_success() {
        String brokerId = "broker1";
        brokerMapper.updateLastLogin(brokerId);

        Optional<Broker> brokerOptional = brokerMapper.findById(brokerId);

        assertThat(brokerOptional).isPresent();
        Broker broker = brokerOptional.get();
        assertThat(broker.getLastLogin()).isNotNull();

        LocalDateTime now = LocalDateTime.now();
        assertThat(broker.getLastLogin()).isAfter(now.minusMinutes(1));
    }
}